package App;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;




public class MainWindow extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField jTextRuta;
	private JTextField JTextField3;
	private JTextField JTextField4;
	private JTextField JTextField5;
	private JTextField JTextField6;
	private JTextField JTextField7;
	private JTextField JTextField8;
	private JTextField JTextField9;
	private JTextField JTextField10;
	private JTextField JTextFieldInputWCB;
	private JTextField JTextFieldOutputWCB;
	
	
	/**
	 * Ejecuta la App
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea el GUI
	 */
	public MainWindow() {
		
		setTitle("TAGer v. 1.3 (Vinfra S.A. All rights reserved)");
		setResizable(false);
		setMaximumSize(new Dimension(800, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1061, 655);
		contentPane = new JPanel();
		contentPane.setMaximumSize(new Dimension(800, 600));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(UIManager.getBorder("TextArea.border"));
		tabbedPane.setForeground(Color.DARK_GRAY);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 11));
		tabbedPane.setBounds(0, 0, 1055, 627);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("EXIF // IPTC", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Lanzar .WCB", null, panel_1, null);
		panel.setLayout(null);
		
		final JTextArea JTextArea1 = new JTextArea();
		JTextArea1.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 13));
		JTextArea1.setBounds(10,67,550,410);
		DefaultCaret caret = (DefaultCaret)JTextArea1.getCaret();
		JScrollPane scroll=new JScrollPane(JTextArea1);
		scroll.setBounds(10,67,550,410);
		panel.add(scroll);
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		final JTextArea JTextAreaWCB = new JTextArea();
		JTextAreaWCB.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 13));
		JTextAreaWCB.setBounds(10, 118, 1007, 388);
		DefaultCaret caretwcb = (DefaultCaret)JTextAreaWCB.getCaret();
		JScrollPane scrollwcb=new JScrollPane(JTextAreaWCB);
		scrollwcb.setBounds(10,118,1007,388);
		panel_1.add(scrollwcb);
		caretwcb.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		
		final JCheckBox chckbxTiff = new JCheckBox("TIFF");
		chckbxTiff.setBounds(770, 454, 58, 23);
		panel.add(chckbxTiff);
		chckbxTiff.setForeground(Color.BLUE);
		
		final JCheckBox chckbxPdf = new JCheckBox("PDF");
		chckbxPdf.setBounds(897, 454, 67, 23);
		panel.add(chckbxPdf);
		chckbxPdf.setForeground(Color.RED);
		
		final JCheckBox chkboxSignatura = new JCheckBox("Extraer la signatura del directorio");
		chkboxSignatura.setBounds(755, 159, 232, 23);
		chkboxSignatura.setForeground(Color.DARK_GRAY);
		chkboxSignatura.setFont(new Font("Tahoma", Font.BOLD, 11));
		chkboxSignatura.setHorizontalTextPosition(SwingConstants.RIGHT);
		panel.add(chkboxSignatura);
		
		final JCheckBox chckbxFechaSistema = new JCheckBox("Aplicar Fecha del Sistema");
		chckbxFechaSistema.setForeground(Color.DARK_GRAY);
		chckbxFechaSistema.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxFechaSistema.setBounds(755, 389, 181, 23);
		panel.add(chckbxFechaSistema);
		
		final JCheckBox chckbxJpeg = new JCheckBox("JPEG");
		chckbxJpeg.setBounds(830, 454, 65, 23);
		panel.add(chckbxJpeg);
		chckbxJpeg.setForeground(new Color(210, 105, 30));
		
		
		
		JButton jButtRuta = new JButton("Seleccionar");
		jButtRuta.setBounds(440, 29, 118, 25);
		panel.add(jButtRuta);
		
		jButtRuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser directorio = new JFileChooser();
				directorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				
				int status = directorio.showOpenDialog(null);
					
				// PASAMOS EL VALOR DEL DIRECTORIO SELECIONADO A jTextFieldInputWCB
				if (status == JFileChooser.APPROVE_OPTION){
					jTextRuta.setText( directorio.getSelectedFile().toString());	
				}	
				
				
			}
		});
		
		
		
				
		JLabel lblNewLabel = new JLabel("Seleccionar carpeta para procesar");
		lblNewLabel.setBounds(10, 11, 200, 19);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(70, 130, 180));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jTextRuta = new JTextField();
		jTextRuta.setBounds(10, 31, 407, 20);
		panel.add(jTextRuta);
		jTextRuta.setFont(new Font("Tahoma", Font.BOLD, 11));
		jTextRuta.setForeground(Color.BLACK);
		jTextRuta.setDragEnabled(true);
		jTextRuta.setColumns(10);
		
		JLabel lblIptc = new JLabel("IPTC");
		lblIptc.setBounds(953, 32, 34, 19);
		panel.add(lblIptc);
		lblIptc.setForeground(new Color(70, 130, 180));
		lblIptc.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblTtulo = new JLabel("T\u00EDtulo:");
		lblTtulo.setBounds(701, 70, 45, 19);
		panel.add(lblTtulo);
		lblTtulo.setForeground(Color.DARK_GRAY);
		lblTtulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JTextField3 = new JTextField();
		JTextField3.setBounds(756, 67, 231, 20);
		panel.add(JTextField3);
		JTextField3.setFont(new Font("Tahoma", Font.BOLD, 11));
		JTextField3.setColumns(10);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(701, 99, 45, 19);
		panel.add(lblAutor);
		lblAutor.setForeground(Color.DARK_GRAY);
		lblAutor.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JTextField4 = new JTextField();
		JTextField4.setBounds(756, 98, 231, 20);
		panel.add(JTextField4);
		JTextField4.setFont(new Font("Tahoma", Font.BOLD, 11));
		JTextField4.setColumns(10);
		
		JTextField5 = new JTextField();
		JTextField5.setBounds(756, 130, 231, 20);
		panel.add(JTextField5);
		JTextField5.setFont(new Font("Tahoma", Font.BOLD, 11));
		JTextField5.setColumns(10);
		
		JLabel lblAsunto = new JLabel("Asunto / Signatura:");
		lblAsunto.setBounds(625, 132, 120, 19);
		panel.add(lblAsunto);
		lblAsunto.setForeground(Color.DARK_GRAY);
		lblAsunto.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblCopyright = new JLabel("Copyright:");
		lblCopyright.setBounds(673, 194, 65, 19);
		panel.add(lblCopyright);
		lblCopyright.setForeground(Color.DARK_GRAY);
		lblCopyright.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JTextField6 = new JTextField();
		JTextField6.setBounds(756, 193, 231, 20);
		panel.add(JTextField6);
		JTextField6.setFont(new Font("Tahoma", Font.BOLD, 11));
		JTextField6.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(625, 224, 362, 2);
		panel.add(separator);
		
		JLabel lblExif = new JLabel("EXIF");
		lblExif.setBounds(953, 237, 34, 19);
		panel.add(lblExif);
		lblExif.setForeground(new Color(70, 130, 180));
		lblExif.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblCopyright_1 = new JLabel("Copyright:");
		lblCopyright_1.setBounds(681, 264, 65, 19);
		panel.add(lblCopyright_1);
		lblCopyright_1.setForeground(Color.DARK_GRAY);
		lblCopyright_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JTextField7 = new JTextField();
		JTextField7.setBounds(756, 263, 231, 20);
		panel.add(JTextField7);
		JTextField7.setFont(new Font("Tahoma", Font.BOLD, 11));
		JTextField7.setColumns(10);
		
		JLabel lblSoftware = new JLabel("Software:");
		lblSoftware.setBounds(684, 296, 54, 19);
		panel.add(lblSoftware);
		lblSoftware.setForeground(Color.DARK_GRAY);
		lblSoftware.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JTextField8 = new JTextField();
		JTextField8.setBounds(756, 295, 231, 20);
		panel.add(JTextField8);
		JTextField8.setFont(new Font("Tahoma", Font.BOLD, 11));
		JTextField8.setColumns(10);
		
		JLabel lblFabricante = new JLabel("Fabricante:");
		lblFabricante.setBounds(673, 328, 65, 19);
		panel.add(lblFabricante);
		lblFabricante.setForeground(Color.DARK_GRAY);
		lblFabricante.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JTextField9 = new JTextField();
		JTextField9.setBounds(756, 327, 231, 20);
		panel.add(JTextField9);
		JTextField9.setFont(new Font("Tahoma", Font.BOLD, 11));
		JTextField9.setColumns(10);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(693, 358, 45, 19);
		panel.add(lblModelo);
		lblModelo.setForeground(Color.DARK_GRAY);
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JTextField10 = new JTextField();
		JTextField10.setBounds(756, 357, 231, 20);
		panel.add(JTextField10);
		JTextField10.setFont(new Font("Tahoma", Font.BOLD, 11));
		JTextField10.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(625, 445, 362, 2);
		panel.add(separator_1);
		
		JLabel lblTiposDeFichero = new JLabel("Tipos de Fichero:");
		lblTiposDeFichero.setBounds(625, 458, 94, 14);
		panel.add(lblTiposDeFichero);
		lblTiposDeFichero.setForeground(Color.DARK_GRAY);
		lblTiposDeFichero.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		
		final JProgressBar JprogressBar = new JProgressBar();
		JprogressBar.setBounds(596, 550, 187, 19);
		panel.add(JprogressBar);
		JprogressBar.setStringPainted(true);
		
		
		
		
		JButton JButton1 = new JButton("Salir");
		JButton1.setBounds(928, 546, 89, 25);
		panel.add(JButton1);
		JButton1.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		JButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton JButton5 = new JButton("Ejecutar");
		JButton5.setBounds(829, 546, 89, 25);
		panel.add(JButton5);
		
		JLabel lblNewLabel_1 = new JLabel("(Corrige Ficheros Mekel)");
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(770, 420, 194, 14);
		panel.add(lblNewLabel_1);
		
		JButton5.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				
				
				String controlruta = jTextRuta.getText();
				
				if  (controlruta.length() != 0){
					ExivCommand h=new ExivCommand(chckbxTiff.isSelected(), chckbxJpeg.isSelected(), chckbxPdf.isSelected(), JTextField7.getText(), JTextField8.getText(),
							JTextField9.getText(), JTextField10.getText(), JTextField6.getText(), JTextField3.getText(),JTextField4.getText(),JTextField5.getText(),
							jTextRuta.getText(),JTextArea1,JprogressBar, DirectoryFilesCalculate.contar(jTextRuta.getText(), chckbxTiff.isSelected(), chckbxJpeg.isSelected(), 
							chckbxPdf.isSelected()), chkboxSignatura.isSelected(), chckbxFechaSistema.isSelected());
			        
			        h.execute();
					
				}else{
					
					JOptionPane.showMessageDialog(null,"Debe seleccionar la ruta a los ficheros");
					
				}
				
				
				
				
			}
		});
		panel_1.setLayout(null);
		
			
		JLabel JLabelseleccionarWCB = new JLabel("Seleccionar carpeta para procesar");
		JLabelseleccionarWCB.setBounds(10, 11, 200, 19);
		JLabelseleccionarWCB.setForeground(new Color(70, 130, 180));
		JLabelseleccionarWCB.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(JLabelseleccionarWCB);
		
		JTextFieldInputWCB = new JTextField();
		JTextFieldInputWCB.setText("D:\\PRUEBA\\wcb");
		JTextFieldInputWCB.setHorizontalAlignment(SwingConstants.LEFT);
		JTextFieldInputWCB.setBounds(10, 31, 407, 20);
		JTextFieldInputWCB.setForeground(Color.BLACK);
		JTextFieldInputWCB.setFont(new Font("Tahoma", Font.BOLD, 11));
		JTextFieldInputWCB.setDragEnabled(true);
		JTextFieldInputWCB.setColumns(10);
		panel_1.add(JTextFieldInputWCB);
		
		JButton JbuttonSeleccionarWCB = new JButton("Seleccionar");
		JbuttonSeleccionarWCB.setBounds(440, 29, 116, 25);
		panel_1.add(JbuttonSeleccionarWCB);
		
		JLabel JLabeldestinoWCB = new JLabel("Seleccionar carpeta destino");
		JLabeldestinoWCB.setBounds(10, 62, 200, 19);
		JLabeldestinoWCB.setForeground(new Color(70, 130, 180));
		JLabeldestinoWCB.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(JLabeldestinoWCB);
		
		JTextFieldOutputWCB = new JTextField();
		JTextFieldOutputWCB.setBounds(10, 87, 407, 20);
		panel_1.add(JTextFieldOutputWCB);
		JTextFieldOutputWCB.setColumns(10);
		
		JButton JbuttonSeleccionarWCB2 = new JButton("Seleccionar");
		JbuttonSeleccionarWCB2.setBounds(440, 86, 116, 23);
		panel_1.add(JbuttonSeleccionarWCB2);
		
		final JSpinner spinner = new JSpinner();
		spinner.setBounds(974, 31, 43, 20);
		spinner.setModel(new SpinnerNumberModel(0, 0, 4, 0.1));
		spinner.setEnabled(false);
		
		panel_1.add(spinner);
		
		final JCheckBox ChkboxMarcoWCB = new JCheckBox("Marco");
		ChkboxMarcoWCB.setBounds(800, 31, 80, 23);
		ChkboxMarcoWCB.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (ChkboxMarcoWCB.isSelected()){
					spinner.setEnabled(true);
				}else{
					spinner.setEnabled(false);
				}
			}
		});
		ChkboxMarcoWCB.setHorizontalTextPosition(SwingConstants.LEFT);
		panel_1.add(ChkboxMarcoWCB);
		
		final JProgressBar progressBarWCB = new JProgressBar();
		progressBarWCB.setStringPainted(true);
		progressBarWCB.setBounds(596, 550, 187, 19);
		panel_1.add(progressBarWCB);
		
		
		JLabel lblTamaoDelMarco = new JLabel("Tama\u00F1o del Marco:");
		lblTamaoDelMarco.setBounds(883, 35, 94, 14);
		panel_1.add(lblTamaoDelMarco);
		
		JButton btnSalirWCB = new JButton("Salir");
		btnSalirWCB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		btnSalirWCB.setBounds(928, 546, 89, 25);
		panel_1.add(btnSalirWCB);
		
		JButton btnEjecutarWCB = new JButton("Ejecutar");
		btnEjecutarWCB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				MainWCBProcess corta = new MainWCBProcess(JTextFieldInputWCB.getText(), JTextFieldOutputWCB.getText(), progressBarWCB, JTextAreaWCB, 
												ChkboxMarcoWCB.isSelected(),  (Double) spinner.getValue(), WCBFilesCalculate.totalimagecount(JTextFieldInputWCB.getText()));
				
				
				
				corta.execute();
			}
		});
		
		
		btnEjecutarWCB.setBounds(829, 546, 89, 25);
		panel_1.add(btnEjecutarWCB);
		
		
		
		
		
				
		JbuttonSeleccionarWCB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			JFileChooser directorioWCB = new JFileChooser();
			directorioWCB.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			
			
			int statusWCB = directorioWCB.showOpenDialog(null);
				
			// PASAMOS EL VALOR DEL DIRECTORIO SELECIONADO A jTextFieldInputWCB
			if (statusWCB == JFileChooser.APPROVE_OPTION){
				JTextFieldInputWCB.setText( directorioWCB.getSelectedFile().toString());	
			} 
			}
		});
		
		
		
		
		Dialog dialog = new Dialog((Window) null);
		dialog.setBounds(657, 392, 200, 50);
	}
}
