package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Erregistratua;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

import  javax.swing.SwingWorker;

public class runGUI2 extends JFrame {
	private int irabazlea=-1;
	
	public int getWinner() {
		return irabazlea;
	}

	public void setWinner(int winner) {
		this.irabazlea = winner;
	}
	
	private JProgressBar bar = new JProgressBar();
	private JProgressBar bar_1_1 = new JProgressBar();
	private JProgressBar bar_1_2 = new JProgressBar();
	private JProgressBar bar_1 = new JProgressBar();

	JFrame frame = new JFrame();
	private JLabel winner;
	private final JLabel lblNewLabel = new JLabel("");
	private final JLabel lblNewLabel_1 = new JLabel("");
	private final JLabel lblNewLabel_2 = new JLabel("");
	private final JLabel lblNewLabel_3 = new JLabel("");
	private final JLabel lblNewLabel_4 = new JLabel("");
	private final JLabel lblNewLabel_1_1 = new JLabel("");
	private final JLabel lblNewLabel_5 = new JLabel("");
	private final JLabel lblNewLabel_1_1_1 = new JLabel("");
	private final JLabel lblNewLabel_2_1 = new JLabel("");
	private final JLabel lblNewLabel_3_1 = new JLabel("");
	private runGUI2 nireF = this;
	private JFrame R = null;
	private final JButton btnNewButton_1 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Start"));
	private final JLabel lblNewLabel_6_1 = new JLabel("");
	private Erregistratua user;
	private int apostua;
	private BLFacade facade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		runGUI2 frame = new runGUI2(null,null,-1,null);

	}

	/**
	 * Create the frame.
	 */
	public runGUI2(JFrame r,Erregistratua user, int apostua,BLFacade facade) {
		R=r;
		this.user = user;
		this.apostua = apostua;
		this.facade = facade;
	
		
		initialize(r);

	}

	public void initialize(JFrame r) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		bar.setIndeterminate(true);
		bar_1.setIndeterminate(true);
		bar_1_1.setIndeterminate(true);
		bar_1_2.setIndeterminate(true);
		
		bar.setForeground(Color.RED);
		bar.setValue(0);
		bar.setBounds(10, 24, 384, 29);
		bar.setStringPainted(true);

		bar_1.setForeground(Color.BLUE);
		bar_1.setValue(0);
		bar_1.setStringPainted(true);
		bar_1.setBounds(10, 64, 384, 29);

		bar_1_1.setValue(0);
		bar_1_1.setStringPainted(true);
		bar_1_1.setForeground(Color.YELLOW);
		bar_1_1.setBounds(10, 104, 384, 29);

		bar_1_2.setValue(0);
		bar_1_2.setStringPainted(true);
		bar_1_2.setForeground(Color.MAGENTA);
		bar_1_2.setBounds(10, 144, 384, 29);

		frame.getContentPane().add(bar);
		frame.getContentPane().add(bar_1);
		frame.getContentPane().add(bar_1_1);
		frame.getContentPane().add(bar_1_2);

		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(563, 420);
		frame.getContentPane().setLayout(null);

		winner = new JLabel("");
		winner.setFont(new Font("Tahoma", Font.BOLD, 20));
		winner.setBounds(10, 217, 480, 65);
		frame.getContentPane().add(winner);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(400, 24, 46, 29);
		
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setBounds(400, 52, 46, 29);
		
		frame.getContentPane().add(lblNewLabel_1);
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setBounds(400, 81, 46, 29);
		
		frame.getContentPane().add(lblNewLabel_2);
		lblNewLabel_3.setBackground(Color.BLACK);
		lblNewLabel_3.setBounds(400, 104, 46, 40);
		
		frame.getContentPane().add(lblNewLabel_3);
		lblNewLabel_4.setBackground(Color.WHITE);
		lblNewLabel_4.setBounds(400, 144, 46, 29);
		
		lblNewLabel.setOpaque(true);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_4.setOpaque(true);
		
		frame.getContentPane().add(lblNewLabel_4);
		lblNewLabel_1_1.setOpaque(true);
		lblNewLabel_1_1.setBackground(Color.BLACK);
		lblNewLabel_1_1.setBounds(444, 24, 46, 29);
		
		frame.getContentPane().add(lblNewLabel_1_1);
		lblNewLabel_5.setOpaque(true);
		lblNewLabel_5.setBackground(Color.WHITE);
		lblNewLabel_5.setBounds(444, 52, 46, 29);
		
		frame.getContentPane().add(lblNewLabel_5);
		lblNewLabel_1_1_1.setOpaque(true);
		lblNewLabel_1_1_1.setBackground(Color.BLACK);
		lblNewLabel_1_1_1.setBounds(444, 81, 46, 29);
		
		frame.getContentPane().add(lblNewLabel_1_1_1);
		lblNewLabel_2_1.setOpaque(true);
		lblNewLabel_2_1.setBackground(Color.WHITE);
		lblNewLabel_2_1.setBounds(444, 110, 46, 29);
		
		frame.getContentPane().add(lblNewLabel_2_1);
		lblNewLabel_3_1.setOpaque(true);
		lblNewLabel_3_1.setBackground(Color.BLACK);
		lblNewLabel_3_1.setBounds(444, 133, 46, 40);
		
		frame.getContentPane().add(lblNewLabel_3_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_1.setEnabled(false);
				Thread hilo = new Thread(new Runnable() {
					
                    @Override
                    public void run() {
                        fill(r);
                        
                    }
                });         
				hilo.start();
				
				
            }
		});
		btnNewButton_1.setBounds(194, 314, 89, 23);
		
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setOpaque(true);
		lblNewLabel_6.setBackground(new Color(107, 142, 35));
		lblNewLabel_6.setBounds(0, 173, 547, 40);
		frame.getContentPane().add(lblNewLabel_6);
		lblNewLabel_6_1.setOpaque(true);
		lblNewLabel_6_1.setBackground(new Color(107, 142, 35));
		lblNewLabel_6_1.setBounds(0, 1, 547, 172);
		
		frame.getContentPane().add(lblNewLabel_6_1);

		frame.setVisible(true);
		
		//fill();
	}
	public int fill(JFrame r) {
		winner.setText("READY.....");
		
		bar.setIndeterminate(false);
		bar_1.setIndeterminate(false);
		bar_1_1.setIndeterminate(false);
		bar_1_2.setIndeterminate(false);
		

		int lap = 0;

		int i = 0;
		int i2 = 0;
		int i3 = 0;
		int i4 = 0;

		int emaitza = 0;

		bar.setValue(0);
		bar_1.setValue(0);
		bar_1_1.setValue(0);
		bar_1_2.setValue(0);

		boolean bukatuta=false;

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		winner.setText("GO! GO! GO!");

		while (!bukatuta) {

			bar.setValue(i);
			bar_1.setValue(i2);
			bar_1_1.setValue(i3);
			bar_1_2.setValue(i4);

			try {
				Thread.sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		//	try {
		//		Thread.sleep(40);
		//	} catch (InterruptedException e) {
		//		// TODO Auto-generated catch block
		//		e.printStackTrace();
		//	}

			i += 1 + (int) (Math.random() * ((5 - 1) + 1));
			i2 += 1 + (int) (Math.random() * ((5 - 1) + 1));
			i3 += 1 + (int) (Math.random() * ((5 - 1) + 1));
			i4 += 1 + (int) (Math.random() * ((5 - 1) + 1));

			if (bar.getValue() == 100) {
				bukatuta=true;
				if (emaitza == 0)
					emaitza = 1;
				break;
			}
			if (bar_1.getValue() == 100) {
				bukatuta=true;
				if (emaitza == 0)
					emaitza = 2;
				break;
			}
			if (bar_1_1.getValue() == 100) {
				bukatuta=true;
				if (emaitza == 0)
					emaitza = 3;
				break;
			}
			if (bar_1_2.getValue() == 100) {
				bukatuta=true;
				if (emaitza == 0)
					emaitza = 4;
				break;
			}

		}
		winner.setText(String.valueOf("The winner is the horse nº " + emaitza));
		//nireF.setVisible(false);
		setWinner(emaitza);
		facade.HorseApostu(getWinner()-1, user, apostua);
		
		return emaitza;
		
		
	}
}
