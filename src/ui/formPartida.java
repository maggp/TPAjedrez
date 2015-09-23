package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.Partida;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import negocio.CtrlAjedrez;
import appExceptions.ApplicationException;

import com.mysql.jdbc.PreparedStatement;

import data.FactoryConexion;
import entidades.Jugador;
import entidades.Partida;
import entidades.Pieza;
import entidades.Posicion;

public class formPartida extends JFrame {

	private JPanel contentPane;
	private JTextField txtJugadorBlancas;
	private JTextField txtJugadorNegras;
	private JTextField txtTurnoJugador;
	private JTextField txtDestino;

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
		txtJugadorBlancas.setColumns(10);
		
		JLabel lblNegras = new JLabel("Negras:");
		lblNegras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtJugadorNegras = new JTextField();
		txtJugadorNegras.setColumns(10);
		
		JSeparator separator = new JSeparator();
		
		JLabel lblTurnoJugador = new JLabel("Turno Jugador:");
		lblTurnoJugador.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtTurnoJugador = new JTextField();
		txtTurnoJugador.setColumns(10);
		
		JLabel lblPosicionesBlancas = new JLabel("Posiciones Blancas:");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblPosicionesNegras = new JLabel("Posiciones Negras:");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JLabel lblMovimiento = new JLabel("Movimiento:");
		
		JLabel lblOrigen = new JLabel("Origen:");
		
		JLabel lblDestino = new JLabel("Destino:");
		
		JTextField txtOrigen = new JTextField();
		txtOrigen.setColumns(10);
		
		txtDestino = new JTextField();
		txtDestino.setColumns(10);
		
		JButton btnMover = new JButton("Mover");
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
					.addContainerGap(42, Short.MAX_VALUE))
				.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblTurnoJugador)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTurnoJugador, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(lblPosicionesNegras)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(scrollPane_1, Alignment.LEADING)
								.addComponent(lblPosicionesBlancas, Alignment.LEADING)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))))
					.addPreferredGap(ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblMovimiento))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblOrigen)
								.addComponent(lblDestino))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtDestino, Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
								.addComponent(txtOrigen, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnMover)))
					.addGap(403))
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
							.addComponent(lblMovimiento)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtOrigen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblOrigen))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(lblPosicionesNegras)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtDestino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDestino))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnMover)))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		
		JTextArea txtAreaPosicionesNegras = new JTextArea();
		scrollPane_1.setViewportView(txtAreaPosicionesNegras);
		
		JTextArea txtAreaPosicionesBlanca = new JTextArea();
		scrollPane.setViewportView(txtAreaPosicionesBlanca);
		contentPane.setLayout(gl_contentPane);
	}

	public formPartida(Partida partida) {
		this();
	}

	
	public void moverPieza ( Partida par, Posicion origen, Posicion destino) throws ApplicationException{
		// OBTENGO LAS PIEZAS DE LA PARTIDA 
		HashMap<Posicion, Pieza> piezas = par.getColPiezas();
		// OBTENGO LA PIEZA CORRESPONDIENTE A LA POSICION DE ORIGEN		
		Pieza p = piezas.get(origen);
		// VERIFICO SI ES VALIDO EL MOVIMIENTO DE DESTINO DE ESA PIEZA
		if(p.movimientoValido(destino)){
			// SI ES VALIDO ACTUALIZO LA CLAVE(POSICION) 
			//CORREGIR
			piezas.put(destino, p);
			
			CtrlAjedrez ctrlA = new CtrlAjedrez();
			ctrlA.actualizarMovimiento(par.getIdPartida(),p,destino);
			
		}
		else { 
			// SI NO ES VALIDO EL MOVIMIENTO MUESTRO MENSAJE CON ERROR
			JOptionPane.showMessageDialog(null, "MOVIMIENTO INVALIDO");
		}
		
	}
	protected void mapeoDatosBlancas (Jugador j) {
	    txtJugadorBlancas.setText(j.getNombre());
		
				}

	protected void mapeoDatosNegras (Jugador j) {
	    txtJugadorNegras.setText(j.getNombre());
		
				}
}
