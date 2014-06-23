package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class TelaSentenca extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSentenca dialog = new TelaSentenca();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public TelaSentenca() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setBounds(100, 100, 460, 121);
		this.setResizable(false);
		
		
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 454, 48);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 11, 166, 20);
		panel.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(186, 11, 67, 20);
		panel.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(263, 11, 166, 20);
		panel.add(comboBox_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 47, 454, 42);
		getContentPane().add(panel_1);
		
		JButton btnSalvar = new JButton("Salvar");
		panel_1.add(btnSalvar);

	}
}
