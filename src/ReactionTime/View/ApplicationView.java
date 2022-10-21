package ReactionTime.View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;


import ReactionTime.ReactionTimeApplication;
import ReactionTime.Controller.Controller;//remove later

public class ApplicationView {
	
	private JFrame frame;

	private CardLayout cardLayout;
	private JPanel pageController;

	private CardLayout appMidPanelCardLayout;
	private JPanel appMidPanelpageController;

	private JPanel appPanel;
	private JPanel topAppPanel;
	private JPanel midAppPanel;
	private JPanel botAppPanel;

	private JPanel recordsPanel;
	private JPanel topRecordsPanel;
	private JPanel midRecordsPanel;
	private JPanel botRecordsPanel;

	private JPanel startPanel;
	private JPanel getReadyPanel;
	private JPanel falseStartPanel;
	private JPanel clickPanel;

	private JButton goToRecordsButton;
	private JButton goToAppButton;
	private JButton RemoveLastRecordButton;
	private JButton RemoveAllRecordsButton;

	private JLabel startLabel;
	private JLabel getReadyLabel;
	private JLabel falseStartLabel;
	private JLabel clickNowLabel;

	private final String WINDOW_NAME = "Reaction Time Test";
	private final Dimension WINDOW_SIZE = new Dimension(800, 600);
	private final Dimension PANEL_BAR_SIZE = new Dimension(0, 40);
	
	private final String CARD_NAME_START_PANEL = "startPanel";
	private final String CARD_NAME_GET_READY_PANEL = "getReadyPanel";
	private final String CARD_NAME_FALSE_START_PANEL = "falseStartPanel";
	private final String CARD_NAME_CLICK_PANEL = "clickPanel";
	
	private final String CARD_NAME_APP_PANEL = "appPanel";
	private final String CARD_NAME_RECORDS_PANEL = "RecordsPanel";
	
	private final Dimension BUTTON_SIZE = new Dimension(105, 30);
	private final Dimension BUTTON_SIZE_WIDE = new Dimension(120, 30);
	
	private final String BUTTON_NAME_GO_TO_APP = "Application";
	private final String BUTTON_NAME_REMOVE_LAST = "Remove Last";
	private final String BUTTON_NAME_REMOVE_ALL = "Remove All";
	private final String BUTTON_NAME_GO_TO_RECORDS = "Records";
	
	private final String LABEL_NAME_START_SCREEN = "Click to Start";
	private final String LABEL_NAME_GET_READY_SCREEN = "Get Ready";
	private final String LABEL_NAME_FALSE_START_SCREEN = "Too Soon";
	private final String LABEL_NAME_CLICK_NOW_SCREEN = "Click Now";

	private final String FONT_NAME = "Arial";
	private final Color COLOR_BUTTON = new Color(50, 50, 50);
	private final Color COLOR_BUTTON_TEXT = new Color(200, 200, 200);
	private final Color COLOR_FONT_SCREEN = new Color(255, 255, 255, 150);
	
	private final int BUTTON_TEXT_SIZE = 15;
	private final int FONT_SIZE_SCREEN = 100;
	
	private final Color COLOR_SCREEN_BAR = new Color(0, 0, 0);
	private final Color COLOR_SCREEN_START = new Color(50, 180, 220);
	private final Color COLOR_SCREEN_GET_READY = new Color(160, 160, 160);
	private final Color COLOR_SCREEN_FALSE_START = new Color(230, 0, 0);
	private final Color COLOR_SCREEN_CLICK_NOW = new Color(20, 220, 0);
	private final Color COLOR_RECORDS_BACKGROUND = new Color(50, 180, 220);


	private Controller controller;
	private int randomDelayInMS;
	private int timeElapsedInMS = 0;
	private Timer randomDelayTimer;
	private Timer reactionTimeTimer;
	
	public static void main(String[] args) {
		ReactionTimeApplication ata = new ReactionTimeApplication();
		ApplicationView a = new ApplicationView(ata);
	}

	public ApplicationView(Controller controller) {

		this.controller = controller;
		frame = createFrame(WINDOW_NAME  , WINDOW_SIZE);
		initializePanels();
		initializeButtons();
		initializeTimer();
		setEventAndActionListeners();
		setUpMidPanelCards();
		setUpFrameCards();
		setAppMidPanels();
		setAppPanelComponents();
		setRecordsPanelComponents();

		frame.setVisible(true);
	}




	private JFrame createFrame(String name, Dimension WINDOW_SIZE) {

		frame = new JFrame(name);
		frame.setSize(WINDOW_SIZE);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		return frame;
	}

	private void initializePanels() {

		pageController = new JPanel();
		appMidPanelpageController = new JPanel();

		appPanel = new JPanel();
		topAppPanel = new JPanel();
		midAppPanel = new JPanel();
		botAppPanel = new JPanel();

		recordsPanel = new JPanel();
		topRecordsPanel = new JPanel();
		midRecordsPanel = new JPanel();
		botRecordsPanel = new JPanel();

		startPanel = new JPanel();
		getReadyPanel = new JPanel();
		falseStartPanel = new JPanel();
		clickPanel = new JPanel();
	}

	

	
	private void initializeButtons() {

		goToAppButton 		= createButton(BUTTON_NAME_GO_TO_APP, 		BUTTON_SIZE,BUTTON_TEXT_SIZE);
		goToRecordsButton 	= createButton(BUTTON_NAME_GO_TO_RECORDS, 	BUTTON_SIZE,BUTTON_TEXT_SIZE);
		RemoveLastRecordButton = createButton(BUTTON_NAME_REMOVE_LAST, 	BUTTON_SIZE_WIDE,BUTTON_TEXT_SIZE);
		RemoveAllRecordsButton = createButton(BUTTON_NAME_REMOVE_ALL, 	BUTTON_SIZE_WIDE,BUTTON_TEXT_SIZE);
	}	
	
	private JButton createButton(String name, Dimension size, int textSize) {
		
		JButton button = new JButton(name);
		button.setPreferredSize(size);
		button.setBorder(new BevelBorder(0));
		button.setBorderPainted(true);
		button.setFocusable(false);
		button.setBackground(COLOR_BUTTON);
		
		button.setForeground(COLOR_BUTTON_TEXT);
		button.setFont(new Font(FONT_NAME,1,textSize));
		
		return button;
	}
	
	private void initializeTimer() {
		randomDelayTimer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timeElapsedInMS = timeElapsedInMS + 100;
				System.out.println(timeElapsedInMS);
				
				if (timeElapsedInMS > randomDelayInMS) {
					if (falseStartPanel.isShowing()) {
						System.out.println("FALSE START IS SHOWING HAHA");
						

					} else {
						appMidPanelCardLayout.show(appMidPanelpageController, CARD_NAME_CLICK_PANEL);
						
						if(clickPanel.isShowing()) {
							InitializeReactionSpeedTimer();
							reactionTimeTimer.start();
						}

	
					}
			
					timeElapsedInMS = 0;
					randomDelayTimer.stop();
				}
			}


		});
	}

	
	private void InitializeReactionSpeedTimer() {

		long initialTimeStamp = System.currentTimeMillis();

		reactionTimeTimer = new Timer(1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				clickNowLabel.setText(Long.toString((System.currentTimeMillis() - initialTimeStamp)));

			}
		});
	}
	
	
	private void setEventAndActionListeners() {

		startPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("click");
				randomDelayInMS = controller.getRandomDelay();
				System.out.println("random : "+randomDelayInMS);
				randomDelayTimer.start();
				appMidPanelCardLayout.show(appMidPanelpageController, CARD_NAME_GET_READY_PANEL);

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}
		});

		getReadyPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("FalseStart");
				randomDelayTimer.stop();
				timeElapsedInMS = 0;
				appMidPanelCardLayout.show(appMidPanelpageController, CARD_NAME_FALSE_START_PANEL);
			}
		});

		clickPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			reactionTimeTimer.stop();
			appMidPanelCardLayout.show(appMidPanelpageController, CARD_NAME_START_PANEL);
			startLabel.setText(clickNowLabel.getText());
			}
		});

		falseStartPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				startLabel.setText(LABEL_NAME_CLICK_NOW_SCREEN);
				appMidPanelCardLayout.show(appMidPanelpageController, CARD_NAME_START_PANEL);
			}
		});

		RemoveLastRecordButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.clearLastResult();
			}
		});

		RemoveAllRecordsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.clearResults();
			}
		});

		goToRecordsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pageController, CARD_NAME_RECORDS_PANEL);
			}
		});

		goToAppButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pageController, CARD_NAME_APP_PANEL);
			}
		});
	}

	
	private void setUpMidPanelCards() {
		
		appMidPanelCardLayout = new CardLayout();
		appMidPanelpageController.setLayout(appMidPanelCardLayout);
		midAppPanel.setLayout(new BorderLayout());
		midAppPanel.add(appMidPanelpageController);
		appMidPanelpageController.add(startPanel, CARD_NAME_START_PANEL);
		appMidPanelpageController.add(getReadyPanel, CARD_NAME_GET_READY_PANEL);
		appMidPanelpageController.add(falseStartPanel, CARD_NAME_FALSE_START_PANEL);
		appMidPanelpageController.add(clickPanel, CARD_NAME_CLICK_PANEL);
		appMidPanelCardLayout.show(appMidPanelpageController, CARD_NAME_START_PANEL);
	}

	
	private void setUpFrameCards() {

		cardLayout = new CardLayout();
		pageController.setLayout(cardLayout);
		pageController.add(appPanel, CARD_NAME_APP_PANEL);
		pageController.add(recordsPanel, CARD_NAME_RECORDS_PANEL);
		cardLayout.show(pageController, CARD_NAME_APP_PANEL);
		frame.add(pageController);
	}

	
	private void setAppMidPanels() {
		
		startLabel = createLabel(LABEL_NAME_START_SCREEN);
		setPanel(startPanel, COLOR_SCREEN_START, startLabel);

		getReadyLabel = createLabel(LABEL_NAME_GET_READY_SCREEN);
		setPanel(getReadyPanel, COLOR_SCREEN_GET_READY, getReadyLabel);

		falseStartLabel = createLabel(LABEL_NAME_FALSE_START_SCREEN);
		setPanel(falseStartPanel, COLOR_SCREEN_FALSE_START, falseStartLabel);

		clickNowLabel = createLabel(LABEL_NAME_CLICK_NOW_SCREEN);
		setPanel(clickPanel, COLOR_SCREEN_CLICK_NOW, clickNowLabel);
	}

	
	private void setPanel(JPanel panel, Color bg, JLabel label) {
		
		panel.setBackground(bg);
		panel.setLayout(new BorderLayout());
		panel.add(label);
	}

	
	private JLabel createLabel(String name) {
		
		JLabel label = new JLabel(name);
		label.setForeground(COLOR_FONT_SCREEN);
		label.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE_SCREEN));
		label.setHorizontalAlignment(JLabel.CENTER);
		
		return label;
	}

	
	private void setAppPanelComponents() {

		appPanel.setLayout(new BorderLayout());
		topAppPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		topAppPanel.setBackground(COLOR_SCREEN_BAR);

		botAppPanel.setBackground(COLOR_SCREEN_BAR);

		topAppPanel.setPreferredSize(PANEL_BAR_SIZE);
		botAppPanel.setPreferredSize(PANEL_BAR_SIZE);

		topAppPanel.add(goToRecordsButton);

		appPanel.add(topAppPanel, BorderLayout.NORTH);
		appPanel.add(midAppPanel, BorderLayout.CENTER);
		appPanel.add(botAppPanel, BorderLayout.SOUTH);
	}

	
	private void setRecordsPanelComponents() {

		recordsPanel.setLayout(new BorderLayout());
		topRecordsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		botRecordsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		topRecordsPanel.setBackground(COLOR_SCREEN_BAR);
		midRecordsPanel.setBackground(COLOR_RECORDS_BACKGROUND);
		botRecordsPanel.setBackground(COLOR_SCREEN_BAR);

		topRecordsPanel.setPreferredSize(PANEL_BAR_SIZE);
		botRecordsPanel.setPreferredSize(PANEL_BAR_SIZE);

		topRecordsPanel.add(goToAppButton);
		botRecordsPanel.add(RemoveLastRecordButton);
		botRecordsPanel.add(RemoveAllRecordsButton);

		recordsPanel.add(topRecordsPanel, BorderLayout.NORTH);
		recordsPanel.add(midRecordsPanel, BorderLayout.CENTER);
		recordsPanel.add(botRecordsPanel, BorderLayout.SOUTH);
	}
}