import javax.swing.*;

public class StopWatch {
    private static int seconds = 0;
    private static Timer timer;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Stopwatch");
        JLabel timeLabel = new JLabel("Time: 0 sec");
        timeLabel.setBounds(130, 30, 200, 30);

        JButton startBtn = new JButton("Start");
        startBtn.setBounds(30, 80, 80, 30);

        JButton stopBtn = new JButton("Stop");
        stopBtn.setBounds(130, 80, 80, 30);

        JButton resetBtn = new JButton("Reset");
        resetBtn.setBounds(230, 80, 80, 30);

        timer = new Timer(1000, e -> {
            seconds++;
            timeLabel.setText("Time: " + seconds + " sec");
        });

        startBtn.addActionListener(e -> timer.start());
        stopBtn.addActionListener(e -> timer.stop());
        resetBtn.addActionListener(e -> {
            timer.stop();
            seconds = 0;
            timeLabel.setText("Time: 0 sec");
        });

        frame.add(timeLabel);
        frame.add(startBtn);
        frame.add(stopBtn);
        frame.add(resetBtn);

        frame.setSize(360, 200);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
