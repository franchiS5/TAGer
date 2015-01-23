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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultCaret;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.ImageIcon;

import java.io.File;





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
		setBounds(100, 100, 1055, 655);
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
		
		final JPanel panel = new JPanel();
		tabbedPane.addTab("EXIF // IPTC", null, panel, null);
		
		final JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Lanzar .WCB", null, panel_1, null);
		panel.setLayout(null);
		
		final JTextArea JTextArea1 = new JTextArea();
		JTextArea1.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 13));
		JTextArea1.setBounds(10,67,544,410);
		DefaultCaret caret = (DefaultCaret)JTextArea1.getCaret();
		JScrollPane scroll=new JScrollPane(JTextArea1);
		scroll.setBounds(10,67,544,410);
		panel.add(scroll);
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		final JTextArea JTextAreaWCB = new JTextArea();
		JTextAreaWCB.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 13));
		JTextAreaWCB.setBounds(10, 130, 548, 415);
		DefaultCaret caretwcb = (DefaultCaret)JTextAreaWCB.getCaret();
		JScrollPane scrollwcb=new JScrollPane(JTextAreaWCB);
		scrollwcb.setBounds(10, 130, 548, 415);
		panel_1.add(scrollwcb);
		caretwcb.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		panel_1.setLayout(null);
		
		final JCheckBox chckbxTiff = new JCheckBox("TIFF");
		chckbxTiff.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxTiff.setBounds(760, 424, 58, 23);
		panel.add(chckbxTiff);
		chckbxTiff.setForeground(Color.BLUE);
		
		final JCheckBox chckbxPdf = new JCheckBox("PDF");
		chckbxPdf.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxPdf.setBounds(929, 424, 58, 23);
		panel.add(chckbxPdf);
		chckbxPdf.setForeground(Color.RED);
		
		final JCheckBox chkboxSignatura = new JCheckBox("Extraer la signatura del directorio");
		chkboxSignatura.setBounds(755, 159, 232, 23);
		chkboxSignatura.setForeground(Color.DARK_GRAY);
		chkboxSignatura.setFont(new Font("Tahoma", Font.BOLD, 11));
		chkboxSignatura.setHorizontalTextPosition(SwingConstants.RIGHT);
		panel.add(chkboxSignatura);
		
		final JCheckBox chckbxFechaSistema = new JCheckBox("Fecha del Sistema (TIFF Mekel)");
		chckbxFechaSistema.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxFechaSistema.setForeground(Color.DARK_GRAY);
		chckbxFechaSistema.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxFechaSistema.setBounds(760, 389, 223, 23);
		panel.add(chckbxFechaSistema);
		
		final JCheckBox chckbxJpeg = new JCheckBox("JPEG");
		chckbxJpeg.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxJpeg.setBounds(841, 424, 65, 23);
		panel.add(chckbxJpeg);
		chckbxJpeg.setForeground(new Color(210, 105, 30));
		
		
		final JButton jButtRuta = new JButton("Seleccionar");
		jButtRuta.setIcon(new ImageIcon(MainWindow.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		jButtRuta.setBounds(416, 29, 142, 25);
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
		lblNewLabel.setBounds(10, 11, 245, 19);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(70, 130, 180));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblIptc = new JLabel("IPTC");
		lblIptc.setBounds(953, 32, 34, 19);
		panel.add(lblIptc);
		lblIptc.setForeground(new Color(70, 130, 180));
		lblIptc.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblTtulo = new JLabel("T\u00EDtulo:");
		lblTtulo.setHorizontalAlignment(SwingConstants.RIGHT);
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
		lblAutor.setHorizontalAlignment(SwingConstants.RIGHT);
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
		lblAsunto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAsunto.setBounds(596, 132, 149, 19);
		panel.add(lblAsunto);
		lblAsunto.setForeground(Color.DARK_GRAY);
		lblAsunto.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblCopyright = new JLabel("Copyright:");
		lblCopyright.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCopyright.setBounds(669, 194, 77, 19);
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
		lblCopyright_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCopyright_1.setBounds(669, 264, 77, 19);
		panel.add(lblCopyright_1);
		lblCopyright_1.setForeground(Color.DARK_GRAY);
		lblCopyright_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JTextField7 = new JTextField();
		JTextField7.setBounds(756, 263, 231, 20);
		panel.add(JTextField7);
		JTextField7.setFont(new Font("Tahoma", Font.BOLD, 11));
		JTextField7.setColumns(10);
		
		JLabel lblSoftware = new JLabel("Software:");
		lblSoftware.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSoftware.setBounds(681, 295, 65, 19);
		panel.add(lblSoftware);
		lblSoftware.setForeground(Color.DARK_GRAY);
		lblSoftware.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JTextField8 = new JTextField();
		JTextField8.setBounds(756, 295, 231, 20);
		panel.add(JTextField8);
		JTextField8.setFont(new Font("Tahoma", Font.BOLD, 11));
		JTextField8.setColumns(10);
		
		JLabel lblFabricante = new JLabel("Fabricante:");
		lblFabricante.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFabricante.setBounds(669, 328, 77, 19);
		panel.add(lblFabricante);
		lblFabricante.setForeground(Color.DARK_GRAY);
		lblFabricante.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JTextField9 = new JTextField();
		JTextField9.setBounds(756, 327, 231, 20);
		panel.add(JTextField9);
		JTextField9.setFont(new Font("Tahoma", Font.BOLD, 11));
		JTextField9.setColumns(10);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModelo.setBounds(701, 358, 45, 19);
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
		lblTiposDeFichero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTiposDeFichero.setBounds(641, 430, 105, 14);
		panel.add(lblTiposDeFichero);
		lblTiposDeFichero.setForeground(Color.DARK_GRAY);
		lblTiposDeFichero.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		
		final JProgressBar JprogressBar = new JProgressBar();
		JprogressBar.setBounds(596, 550, 187, 19);
		panel.add(JprogressBar);
		JprogressBar.setStringPainted(true);
		
		JButton JButton1 = new JButton("Salir");
		JButton1.setIcon(new ImageIcon(MainWindow.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		JButton1.setBounds(928, 546, 105, 25);
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
		
		JLabel JLabelseleccionarWCB = new JLabel("Seleccionar carpeta para procesar");
		JLabelseleccionarWCB.setBounds(10, 11, 229, 19);
		JLabelseleccionarWCB.setForeground(new Color(70, 130, 180));
		JLabelseleccionarWCB.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(JLabelseleccionarWCB);
		
		JButton JbuttonSeleccionarWCB = new JButton("Seleccionar");
		JbuttonSeleccionarWCB.setIcon(new ImageIcon(MainWindow.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		JbuttonSeleccionarWCB.setBounds(413, 29, 143, 25);
		panel_1.add(JbuttonSeleccionarWCB);
		
		JLabel JLabeldestinoWCB = new JLabel("Seleccionar carpeta destino");
		JLabeldestinoWCB.setBounds(10, 62, 200, 19);
		JLabeldestinoWCB.setForeground(new Color(70, 130, 180));
		JLabeldestinoWCB.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(JLabeldestinoWCB);
		
		JTextFieldOutputWCB = new JTextField();
		JTextFieldOutputWCB.setBounds(10, 87, 391, 20);
		panel_1.add(JTextFieldOutputWCB);
		JTextFieldOutputWCB.setColumns(10);
		
		JButton JbuttonSeleccionarWCB2 = new JButton("Seleccionar");
		JbuttonSeleccionarWCB2.setIcon(new ImageIcon(MainWindow.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		JbuttonSeleccionarWCB2.setBounds(413, 86, 143, 25);
		panel_1.add(JbuttonSeleccionarWCB2);
		
		final JProgressBar progressBarWCB = new JProgressBar();
		progressBarWCB.setStringPainted(true);
		progressBarWCB.setBounds(596, 550, 187, 19);
		panel_1.add(progressBarWCB);
		
		
		JLabel lblTamaoDelMarco = new JLabel("Tama\u00F1o del Marco:");
		lblTamaoDelMarco.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTamaoDelMarco.setBounds(835, 33, 112, 14);
		panel_1.add(lblTamaoDelMarco);
		
		JButton btnSalirWCB = new JButton("Salir");
		btnSalirWCB.setIcon(new ImageIcon(MainWindow.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		btnSalirWCB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		btnSalirWCB.setBounds(928, 546, 105, 25);
		panel_1.add(btnSalirWCB);
		
		
		final JSpinner spinner = new JSpinner();
		spinner.setBounds(958, 31, 59, 20);
		spinner.setModel(new SpinnerNumberModel(0, 0, 4, 0.1));
		spinner.setEnabled(false);
		
		panel_1.add(spinner);
		
		final JCheckBox ChkboxMarcoWCB = new JCheckBox("Marco");
		ChkboxMarcoWCB.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ChkboxMarcoWCB.setBounds(759, 28, 67, 23);
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
		
		jTextRuta = new JTextField();
		jTextRuta.setBounds(10, 31, 394, 20);
		panel.add(jTextRuta);
		jTextRuta.setFont(new Font("Tahoma", Font.BOLD, 11));
		jTextRuta.setForeground(Color.BLACK);
		jTextRuta.setDragEnabled(true);
		jTextRuta.setColumns(10);
		
		final JButton JButton5 = new JButton("Ejecutar");
		JButton5.setBounds(819, 546, 99, 25);
		//panel.add(JButton5);
		
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
		
		jTextRuta.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    changed();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    changed();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    changed();
			  }

			  public void changed() {
			     if (jTextRuta.getText().equals("")){
			    	 JButton5.setEnabled(false);
			    	 panel.remove(JButton5);
			    	 panel.repaint();
			     }
			     else {
			    	 JButton5.setEnabled(true);
			    	 panel.add(JButton5);
			    	 panel.repaint();
			    }
			  }
			});
		
		final JButton btnEjecutarWCB = new JButton("Ejecutar");
		btnEjecutarWCB.setEnabled(false);
		btnEjecutarWCB.setBounds(819, 546, 99, 25);
		//panel_1.add(btnEjecutarWCB);
		
		btnEjecutarWCB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Double checkvalor = (Double) spinner.getValue();
				int err = 0;
				
				if (ChkboxMarcoWCB.isSelected()){
					if (checkvalor == 0){
						JOptionPane.showMessageDialog(null,"Debe seleccionar un valor distinto de 0 para el marco");
						err = 1;
						
					}
				}
				if (!new File (JTextFieldInputWCB.getText()).exists()){
					JOptionPane.showMessageDialog(null,"La ruta no es correcta");
					err = 1;
				}
				if(err != 1){
					MainWCBProcess corta = new MainWCBProcess(JTextFieldInputWCB.getText(), JTextFieldOutputWCB.getText(), progressBarWCB, JTextAreaWCB, 
							ChkboxMarcoWCB.isSelected(),  (Double) spinner.getValue(), WCBFilesCalculate.totalimagecount(JTextFieldInputWCB.getText()));

					corta.execute();
				}
			}
		});
		
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
		
		
		JTextFieldInputWCB = new JTextField();
		JTextFieldInputWCB.setText("/home/javier/Descargas/PRUEBA/wcb");
		JTextFieldInputWCB.setHorizontalAlignment(SwingConstants.LEFT);
		JTextFieldInputWCB.setBounds(10, 31, 391, 20);
		JTextFieldInputWCB.setForeground(Color.BLACK);
		JTextFieldInputWCB.setFont(new Font("Tahoma", Font.BOLD, 11));
		JTextFieldInputWCB.setDragEnabled(true);
		JTextFieldInputWCB.setColumns(10);
		panel_1.add(JTextFieldInputWCB);
		
		JLabel lblTotalFicherosWCB = new JLabel("Total Ficheros WCB:");
		lblTotalFicherosWCB.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTotalFicherosWCB.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalFicherosWCB.setBounds(10, 553, 105, 16);
		panel_1.add(lblTotalFicherosWCB);
		
		JLabel label = new JLabel("0");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setForeground(new Color(70, 130, 180));
		label.setBounds(116, 553, 37, 16);
		panel_1.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("Total de Imágenes:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(160, 553, 105, 16);
		panel_1.add(lblNewLabel_1);
		
		JLabel label_1 = new JLabel("0");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setForeground(new Color(70, 130, 180));
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setBounds(263, 553, 79, 16);
		panel_1.add(label_1);
		
		// COMPROBAMOS EL CONTENIDO DEL CAMPO DE LA RUTA Y SI CONTIENE ALGO HABILITAMOS EL BOT�N EJECUTAR
		JTextFieldInputWCB.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    changed();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    changed();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    changed();
			  }

			  public void changed() {
			     if (JTextFieldInputWCB.getText().equals("")){
			    	 btnEjecutarWCB.setEnabled(false);
			    	 panel_1.remove(btnEjecutarWCB);
			    	 panel_1.repaint();
			     }
			     else {
			    	 btnEjecutarWCB.setEnabled(true);
			    	 panel_1.add(btnEjecutarWCB);
			    	 panel_1.repaint();
			    }
			  }
			});
		
		Dialog dialog = new Dialog((Window) null);
		dialog.setBounds(657, 392, 200, 50);
	}
}
