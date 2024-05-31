package principal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.KeyValue;
import modelo.Optimizacion;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Cursor;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textAreaResultado;
	private Optimizacion optimizacion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 393);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(44, 44, 44));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Optimizacion de Codigo");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 35));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(69, 69, 69));
		contentPane.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{100, 0, 0, 0, 100, 0, 0, 0, 100, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 0;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JTextArea textAreaCodigo = new JTextArea();
		textAreaCodigo.setText("t1=2-1\r\nt2=t1/2\r\nt3=a*t2\r\nt4=t3*t1\r\nt5=t4+b\r\nt6=a*t2\r\nt7=t6+b\r\nt8=t3*t1\r\nt9=t8+b\r\nt10=a*t2\r\nt11=t3*t1\r\nt12=t11^2\r\nt13=t12*2\r\nc=t13+t1");
//		Ejemplo 5
//		textAreaCodigo.setText("x1=a+b\r\nx2=5*3\r\ny1=c-4\r\nz1=y1+x1\r\nF=x2\r\nx3=d/2\r\nG=z1\r\nH=x3\r\nx4=x3*x2\r\nI=x4");
//		Ejemplo 4
//		textAreaCodigo.setText("a10=a9*a2\r\nc6=5+z\r\na11=a10-5\r\nc1=99\r\nH=b7\r\na1=m+n\r\na6=5*a2\r\nb1=7+2\r\nb5=b4-a6\r\nc5=4-y\r\na9=a8/a3\r\nd4=d3-y\r\na8=a7+a1\r\nd5=d4+5\r\na2=8*2\r\nb2=b1-9\r\nc3=c2/5\r\nG=a5\r\nc2=c1+9\r\nd2=9-d\r\na4=a3+a1\r\nb3=d2+c1\r\na3=p-3\r\nb7=9*b3\r\nb8=b7-b3");
//		Ejemplo 3
//		textAreaCodigo.setText("a10=a9*a2\r\nc6=5+z\r\na11=a10-5\r\nc1=99\r\nH=b7\r\na1=m+n\r\na6=5*a2\r\nb1=7+2\r\nb5=b4-a6\r\nc5=c4-y\r\na9=a8/a3\r\nd4=d3-y\r\na8=a7+a1\r\nd5=d4+5\r\na2=8*2\r\nb2=b1-9\r\nc3=c2/5\r\nG=a5\r\nc2=c1+9\r\nd2=9-d\r\na4=a3+a1\r\nb3=d2+c1\r\na3=p-3\r\nb7=9*b3\r\nb8=b7-b3");
//		Ejemplo 2
//		textAreaCodigo.setText("a1=m+n\r\nb2=b1-a1\r\nc3=c2/5\r\nG=a5\r\nc2=c1+z\r\nd2=d1-d\r\na4=a3+a1\r\nd3=d2+x\r\na3=p-3\r\nd1=d*1\r\nb6=b5/2\r\nb3=b2*3\r\na7=a6-a4\r\nc1=x*y\r\nH=b7\r\nc5=c4-y\r\na6=a5*a2\r\nb1=7+2\r\nb5=b4-a6\r\na9=a8/a3\r\nd4=d3-y\r\na8=a7+a1\r\nd5=d4+5\r\na2=8*2\r\nF=9\r\nb8=b7-b3\r\na5=q/4\r\nd6=d5/2\r\na10=a9*a2\r\nc6=c5+z\r\na11=a10-a5\r\nb4=b3+5\r\na12=a11+a4\r\nc4=c3*x\r\nb7=b6+q\r\no=c4\r\nJ=d6\r\nr =J*o");
//		Ejemplo 1
//		textAreaCodigo.setText("t7=u+v\r\nt1=a-b\r\nt10=t9*3\r\nt4=t3+t7\r\nt5=x/2\r\nt8=t7+t1\r\nt14=t13/t5\r\nt2=6*4\r\nt12=t11-t5\r\nt6=t5*t2\r\nt3=z-2\r\nt9=t8/t3\r\nt15=t14-t10\r\nt13=t12+t4\r\nt11=t10+t6\r\nt16=t15+t11");
		textAreaCodigo.setMargin(new Insets(2, 20, 2, 2));
		textAreaCodigo.setFont(new Font("Arial Black", Font.PLAIN, 18));
//		textAreaCodigo.setText("j=2*2\r\nt1=j+c\r\nj=t1\r\nt2=4+3\r\ni=4\r\nt3=i+2\r\nt4=t3+2\r\nt5=i*1\r\nt6=t5+c\r\na=t6\r\nt7=i*1\r\nt8=t7+c\r\nd=t8\r\nt9=i*1\r\nt10=t9+c\r\nb=t10\r\nt11=b*c\r\ne=t11\r\nt12=a+j\r\na=t2/1\r\nb=a+j");
//		textAreaCodigo.setText("j=2\r\nt1=j+c\r\nj=t1\r\nt2=a*a\r\ni=4\r\nt3=i+2\r\nt4=t3+2\r\nt5=i*1\r\nt6=t5+c\r\nt11=t6*c\r\nt12=t2*2\r\nc=t12*j");
		
//		textAreaCodigo.setText("a=2+3\r\nt1=a+c\r\nj=t1\r\nt2=4+3\r\ni=4\r\nt3=i+2\r\nt4=t3+2\r\nt5=b*c\r\nt6=t5+a\r\na=t6\r\nt7=b*c\r\nt8=t7+a\r\nd=t8\r\nt9=b*c\r\nt10=t9+a\r\nb=t10\r\nt11=b*c\r\ne=t11\r\nt12=a+e\r\na=t2\r\nt13=a+e\r\nd=t13\r\nb=d\r\nt14=d/b\r\nt15=t5*t14\r\nt16=t15^2\r\ne=t16*t16");
		GridBagConstraints gbc_textAreaCodigo = new GridBagConstraints();
		gbc_textAreaCodigo.gridwidth = 3;
		gbc_textAreaCodigo.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaCodigo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textAreaCodigo.gridx = 1;
		gbc_textAreaCodigo.gridy = 1;
		panel_1.add(textAreaCodigo, gbc_textAreaCodigo);
		
		textAreaResultado = new JTextArea();
		textAreaResultado.setEditable(false);
		textAreaResultado.setMargin(new Insets(2, 20, 2, 2));
		textAreaResultado.setFont(new Font("Arial Black", Font.PLAIN, 18));
		GridBagConstraints gbc_textAreaResultado = new GridBagConstraints();
		gbc_textAreaResultado.gridwidth = 3;
		gbc_textAreaResultado.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaResultado.fill = GridBagConstraints.BOTH;
		gbc_textAreaResultado.gridx = 5;
		gbc_textAreaResultado.gridy = 1;
		panel_1.add(textAreaResultado, gbc_textAreaResultado);
		
		JButton btnNewButton = new JButton("Optimizar");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBorder(new MatteBorder(0, 0, 5, 4, (Color) new Color(0, 0, 0)));
		btnNewButton.setFocusPainted(false);
		btnNewButton.setDefaultCapable(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 25));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = textAreaCodigo.getText();
				optimizacion = new Optimizacion();
				llenarTextArea(optimizacion.optimizar(texto));;
				
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.weighty = 0.5;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 2;
		panel_1.add(btnNewButton, gbc_btnNewButton);
	}
	
	public void llenarTextArea(List<KeyValue> resultado) {
		String salida = "";
		for (KeyValue keyValue : resultado) {
			salida += keyValue.toString()+"\n";
		}
		textAreaResultado.setText(salida);
	}

}
