package ui;




import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.Partida;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import negocio.CtrlAjedrez;
import appExceptions.ApplicationException;
import appExceptions.EndGameException;
import entidades.Pieza;
import entidades.Posicion;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class formPartida extends JFrame {

	private JPanel contentPane;
	private JTextField txtJugadorBlancas;
	private JTextField txtJugadorNegras;
	private JTextField txtTurnoJugador;
	private JTextField txtDestinoCol;
	private CtrlAjedrez controlador;
	private Partida partida;
	private JTextArea txtAreaPosicionesBlancas;
	private JTextArea txtAreaPosicionesNegras;
	private JTextField txtDestinoFila;
	private JTextField txtOrigenCol;
	private JTextField txtOrigenFila;
	private JButton btnMover;
	private JLabel lblMovimiento;
	private JLabel lblColumna;
	private JLabel lblFila;
	private JLabel lblOrigen;
	private JLabel lblDestino;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formPartida frame = new formPartida();
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
	public formPartida() {
		setResizable(false);
		setTitle("Jugada");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblBlancas = new JLabel("Blancas:");
		lblBlancas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtJugadorBlancas = new JTextField();
		txtJugadorBlancas.setEditable(false);
		txtJugadorBlancas.setColumns(10);
		
		JLabel lblNegras = new JLabel("Negras:");
		lblNegras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtJugadorNegras = new JTextField();
		txtJugadorNegras.setEditable(false);
		txtJugadorNegras.setColumns(10);
		
		JSeparator separator = new JSeparator();
		
		JLabel lblTurnoJugador = new JLabel("Turno Jugador:");
		lblTurnoJugador.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtTurnoJugador = new JTextField();
		txtTurnoJugador.setEditable(false);
		txtTurnoJugador.setColumns(10);
		
		JLabel lblPosicionesBlancas = new JLabel("Posiciones Blancas:");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblPosicionesNegras = new JLabel("Posiciones Negras:");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		lblMovimiento = new JLabel("Movimiento:");
		
		lblOrigen = new JLabel("Origen:");
		
		lblDestino = new JLabel("Destino:");
		
		txtOrigenCol = new JTextField();
		txtOrigenCol.setColumns(10);
		
		txtDestinoCol = new JTextField();
		txtDestinoCol.setColumns(10);
		
		btnMover = new JButton("Mover");
		btnMover.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnMover_click();
			}
		});
		
		txtOrigenFila = new JTextField();
		txtOrigenFila.setColumns(10);
		
		txtDestinoFila = new JTextField();
		txtDestinoFila.setColumns(10);
		
		lblColumna = new JLabel("Columna:");
		
		lblFila = new JLabel("Fila:");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(34)
					.addComponent(lblBlancas)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtJugadorBlancas, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addGap(75)
					.addComponent(lblNegras)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtJugadorNegras, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(52, Short.MAX_VALUE))
				.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblTurnoJugador)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTurnoJugador, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblPosicionesNegras)
						.addComponent(lblPosicionesBlancas)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
						.addComponent(scrollPane_1))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMovimiento)
								.addComponent(lblDestino)
								.addComponent(lblOrigen))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblColumna)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(txtDestinoCol, Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
									.addComponent(txtOrigenCol, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))))
						.addComponent(btnMover))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtOrigenFila, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtDestinoFila, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFila))
					.addGap(311))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBlancas)
						.addComponent(txtJugadorBlancas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNegras)
						.addComponent(txtJugadorNegras, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTurnoJugador)
						.addComponent(txtTurnoJugador, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addComponent(lblPosicionesBlancas)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMovimiento)
								.addComponent(lblColumna)
								.addComponent(lblFila))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtOrigenCol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtOrigenFila, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblOrigen)
									.addGap(5)))))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(lblPosicionesNegras)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtDestinoCol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDestino)
								.addComponent(txtDestinoFila, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnMover)))
					.addContainerGap(40, Short.MAX_VALUE))
		);
		
		txtAreaPosicionesNegras = new JTextArea();
		scrollPane_1.setViewportView(txtAreaPosicionesNegras);
		
		txtAreaPosicionesBlancas = new JTextArea();
		scrollPane.setViewportView(txtAreaPosicionesBlancas);
		contentPane.setLayout(gl_contentPane);
	}

	protected void btnMover_click() {
		try {
			char colOrigen = this.txtOrigenCol.getText().toLowerCase().trim().toCharArray()[0];
			int filaOrigen = Integer.parseInt(this.txtOrigenFila.getText());
			Posicion posOrigen = new Posicion(colOrigen, filaOrigen);
			char colDestino = this.txtDestinoCol.getText().toLowerCase().trim().toCharArray()[0];
			int filaDestino = Integer.parseInt(this.txtDestinoFila.getText());
			Posicion posDestino = new Posicion(colDestino, filaDestino);
			controlador.moverPieza(posOrigen, posDestino);
			this.llenarFormulario();
			this.borrarContenido();
			
		} catch(EndGameException e){
			this.llenarFormulario();
			ocultarControles();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Juego Finalizado", JOptionPane.INFORMATION_MESSAGE);
		} 
		
		catch (ApplicationException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
		} 
		
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Los campos de fila deben ser un numero entero", "Error", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}

	private void ocultarControles() {
		this.txtDestinoCol.setVisible(false);
		this.txtDestinoFila.setVisible(false);
		this.txtOrigenCol.setVisible(false);
		this.txtOrigenFila.setVisible(false);
		this.btnMover.setVisible(false);
		this.lblColumna.setVisible(false);
		this.lblDestino.setVisible(false);
		this.lblFila.setVisible(false);
		this.lblMovimiento.setVisible(false);
		this.lblOrigen.setVisible(false);
	}
	
	
	public formPartida(Partida partida) {
		this();
		this.partida = partida;
		controlador= new CtrlAjedrez(partida);
		this.llenarFormulario();
	}
	private void llenarFormulario(){
		this.txtJugadorBlancas.setText(partida.getJugadorBlancas().getNombre()+" "+partida.getJugadorBlancas().getApellido());
		this.txtJugadorNegras.setText(partida.getJugadorNegras().getNombre()+" "+partida.getJugadorNegras().getApellido());
		this.txtTurnoJugador.setText(partida.getTurno());
		this.txtAreaPosicionesBlancas.setText("");
		this.txtAreaPosicionesNegras.setText("");
		for (int fila = 1; fila <= 8; fila++) {
			for (char col = 'a'; col <='h'; col++) {
				Pieza p = partida.getColPiezas().get(new Posicion(col, fila));
				if (p!=null) {
					if(p.getColor().equals("blanco")){
						this.txtAreaPosicionesBlancas.setText(txtAreaPosicionesBlancas.getText() + p.getTipoPieza()+
								": "+p.getPosicion().getColumna()+p.getPosicion().getFila()+"\n");
					}
					if(p.getColor().equals("negro")){
						this.txtAreaPosicionesNegras.setText(txtAreaPosicionesNegras.getText() + p.getTipoPieza()+
								": "+p.getPosicion().getColumna()+p.getPosicion().getFila()+"\n");
					}
				}
			}
		}
		
	}
	private void borrarContenido(){
		this.txtDestinoCol.setText("");
		this.txtDestinoFila.setText("");
		this.txtOrigenCol.setText("");
		this.txtOrigenFila.setText("");
	}

	
	
}
