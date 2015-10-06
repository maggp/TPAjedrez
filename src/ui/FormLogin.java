package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import appExceptions.ApplicationException;
import entidades.Jugador;
import entidades.Partida;
import negocio.CtrlAjedrez;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtJugador1;
	private JTextField txtJugador2;
	private CtrlAjedrez controlador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormLogin frame = new FormLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormLogin() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 317, 196);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblJugador1 = new JLabel("DNI Jugador 1:");
		lblJugador1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblJugador2 = new JLabel("DNI Jugador 2:");
		lblJugador2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtJugador1 = new JTextField();
		txtJugador1.setText("11111111");
		txtJugador1.setColumns(10);
		
		txtJugador2 = new JTextField();
		txtJugador2.setText("22222222");
		txtJugador2.setColumns(10);
		
		JButton btnJugar = new JButton("Jugar");
		btnJugar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnJugar_click();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(31)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblJugador2)
								.addComponent(lblJugador1))
							.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtJugador1)
								.addComponent(txtJugador2, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(198, Short.MAX_VALUE)
							.addComponent(btnJugar)))
					.addGap(44))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtJugador1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblJugador1))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtJugador2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblJugador2))
					.addGap(18)
					.addComponent(btnJugar)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		controlador= new CtrlAjedrez();
	}

	protected void btnJugar_click() {
		Jugador j1=null , j2=null;
		formPartida fp = null;
		boolean continuar = true;
		try {
			
			j1 = controlador.identificarJugador(Integer.parseInt(this.txtJugador1.getText()));
			j2 = controlador.identificarJugador(Integer.parseInt(this.txtJugador2.getText()));
			Partida partida = controlador.recuperarPartida(j1,j2);
			if (partida==null) {
				continuar = true;
				partida = new Partida(j1, j2);
				controlador.nuevaPartida(partida);
			} else {
				int respuesta=JOptionPane.showOptionDialog(null, "Existe una partida guardada.\n"
						+ "¿Desea continuar la partida?\n"
						+ "(Si elige no perdera la partida guardada)"
						, "Partida", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
						null, new Object[] { "Si", "No", "Cancelar"}, "Si");
				//Valores para respuesta:
				//SI = 0
				//NO = 1
				//Cancelar = 2
				if(respuesta==0){
					continuar = true;
				}
				if(respuesta==1){
					continuar = true;
					controlador.eliminarPartida(partida.getIdPartida());
					partida = new Partida(j1, j2);
					controlador.nuevaPartida(partida);
				}
				if(respuesta==2){
					continuar=false;
				}
			}
			if(continuar){
				fp = new formPartida(partida);
				fp.setVisible(true);
				this.dispose();
			}
		} catch (NullPointerException e) {
			
			String mensaje="";
			if(j1==null) mensaje+= "No se encontro jugador con el DNI nro. 1\n";
			if(j2==null) mensaje+= "No se enconto jugador con el DNI nro. 2\n";
			JOptionPane.showMessageDialog(null, mensaje, "Error al buscar jugadores", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Los campos de DNI no pueden estar vacíos",
					"Error al buscar jugadores", JOptionPane.INFORMATION_MESSAGE);
		} catch (ApplicationException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

}
