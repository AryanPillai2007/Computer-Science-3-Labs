import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

class Life extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private LifeView view;
	private LifeModel model;
	private JButton runButton, pauseButton, resumeButton, resetButton;

	Life() throws IOException {
		this(null);
	}

	Life(String fileName) throws IOException {
		super("Conway's Life");

		// Build the buttons
		JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		runButton = new JButton("Run");
		runButton.addActionListener(this);
		controlPanel.add(runButton);

		pauseButton = new JButton("Pause");
		pauseButton.addActionListener(this);
		pauseButton.setEnabled(false);
		controlPanel.add(pauseButton);

		resumeButton = new JButton("Resume");
		resumeButton.addActionListener(this);
		resumeButton.setEnabled(false);
		controlPanel.add(resumeButton);

		resetButton = new JButton("Reset");
		resetButton.addActionListener(this);
		resetButton.setEnabled(false);
		controlPanel.add(resetButton);

		// Build the view
		view = new LifeView();
		view.setBackground(Color.white);

		// Put buttons, view together
		Container c = getContentPane();
		c.add(controlPanel, BorderLayout.NORTH);
		c.add(view, BorderLayout.CENTER);

		// Build the model
		model = new LifeModel(view, fileName);
	}

	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b == runButton) {
			model.run();
			runButton.setEnabled(false);
			pauseButton.setEnabled(true);
			resumeButton.setEnabled(false);
			resetButton.setEnabled(true);

		} else if (b == pauseButton) {
			model.pause();
			runButton.setEnabled(false);
			pauseButton.setEnabled(false);
			resumeButton.setEnabled(true);
			resetButton.setEnabled(true);
		} else if (b == resumeButton) {
			model.resume();
			runButton.setEnabled(false);
			pauseButton.setEnabled(true);
			resumeButton.setEnabled(false);
			resetButton.setEnabled(true);
		} else if (b == resetButton) {
			model.reset();
			runButton.setEnabled(true);
			pauseButton.setEnabled(false);
			resumeButton.setEnabled(false);
			resetButton.setEnabled(false);
		}
	}

	public static void main(String[] args) throws IOException {
		Life conway = new Life(); // parameterize to start with a particular file

		conway.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		conway.setSize(570, 640);
		conway.setVisible(true);
	}
}
