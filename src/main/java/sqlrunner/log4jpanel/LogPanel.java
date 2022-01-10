package sqlrunner.log4jpanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.WriterAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;


/**
 * contains a JTextArea for displaying loglines from Appender
 * @author  lolling.jan
 */
public final class LogPanel extends JPanel {

    private static final Logger      logger                  = LogManager.getLogger(LogPanel.class);

    private static final long        serialVersionUID        = 1L;
    private int                      maxLines                = 0;
    private JScrollPane              scrollPane              = null;
    private JTextArea                jTextArea               = null;
    private transient LogWriter                writer;
    private transient PatternLayout            layout                  = null;
    private transient WriterAppender           appender;
    private boolean                  stopped                 = false;
    private boolean                  loggingEnabled          = true;
    private JPanel                   jPanelTools             = null;
    private JButton                  jButtonSaveAs           = null;
    private JButton                  jButtonClear            = null;
    private File                     file;
    private JCheckBox                jCheckBoxAutoScrolling  = null;
    private JCheckBox                jCheckBoxLoggingEnabled = null;
    private JLabel                   jLabel                  = null;
    private String                   suggestedFileName;
    private boolean                  changed                 = false;
    private static LogPanel logPanel;

    /**
     * Contructor for a standard logframe
     */
    public LogPanel() {
    	final LoggerContext context = LoggerContext.getContext(false);
        final Configuration config = context.getConfiguration();
        writer = new LogWriter(this);
        layout = PatternLayout.newBuilder()
        			.withPattern("%d{HH:mm} [%-5p] %-25c - %m%n")
        			.build();
        appender = WriterAppender.newBuilder()
        			.setLayout(layout)
        			.setTarget(writer)
        			.setName("LogPanelAppender")
        			.build();
        appender.start();
        config.addAppender(appender);
        updateLoggers(appender, config);
        initializeGUI();
    }
    
    private void updateLoggers(final Appender appender, final Configuration config) {
        final Level level = null;
        final Filter filter = null;
        for (final LoggerConfig loggerConfig : config.getLoggers().values()) {
            loggerConfig.addAppender(appender, level, filter);
        }
        config.getRootLogger().addAppender(appender, level, filter);
    }

    public static LogPanel getInstance() {
        if (logPanel == null) {
            logPanel = new LogPanel();
        }
        return logPanel;
    }

    private void initializeGUI() {
        setLayout(new BorderLayout());
        this.setSize(new java.awt.Dimension(321, 109));
        scrollPane = new JScrollPane();
        jTextArea = new JTextArea();
        jTextArea.setEditable(false);
        this.add(getJPanelTools(), java.awt.BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(jTextArea);
        changed = false;
    }

    /**
     * @return  the internal appender for use in any logger
     * @uml.property  name="appender"
     */
    public Appender getAppender() {
        return appender;
    }

    public void setMaxLogLines(int maxLines) {
        this.maxLines = maxLines;
    }

    public int getMaxLogLines() {
        return maxLines;
    }

    public void setLineWrap(boolean lineWrap) {
        jTextArea.setLineWrap(lineWrap);
    }

    public boolean getLineWrap() {
        return jTextArea.getLineWrap();
    }

    public void clear() {
        stopped = true;
        jTextArea.setText(null);
        stopped = false;
        changed = false;
    }

    public void setAutoScrolling(boolean auto) {
        this.jCheckBoxAutoScrolling.setSelected(auto);
    }

    public boolean isAutoScrolling() {
        return jCheckBoxAutoScrolling.isSelected();
    }

    /**
     * @param loggingEnabled  the loggingEnabled to set
     * @uml.property  name="loggingEnabled"
     */
    public void setLoggingEnabled(boolean enabled) {
        this.loggingEnabled = enabled;
    }

    /**
     * @return  the loggingEnabled
     * @uml.property  name="loggingEnabled"
     */
    public boolean isLoggingEnabled() {
        return loggingEnabled;
    }

    /**
     * Appends a string to the textarea and scrolls it down.
     * If the number of lines in the area exceeds MAXLINES then the topmost
     * 5 lines will be truncated.
     */
    public void append(String s) {
        if (stopped == false && loggingEnabled) {
            changed = true;
            // Append string
            jTextArea.append(s);
            if (maxLines > 0) {
                // check Linecount
                int len = jTextArea.getLineCount();
                int lines4Delete = (maxLines > 5) ? 5 : maxLines;
                if (len > maxLines) {
                    try {
                        jTextArea.getDocument().remove(0, lines4Delete);
                    } catch (javax.swing.text.BadLocationException exception) {
                        exception.printStackTrace();
                    }
                }
            }
            if (jCheckBoxAutoScrolling.isSelected()) {
                // Scroll down the textarea to the bottom
                Dimension size = jTextArea.getSize();
                JViewport port = scrollPane.getViewport();
                Point point = new Point(0, size.height);
                port.setViewPosition(point);
            }
        }
    }

    public void newLine() {
        append("\n");
    }

    /**
     * LogWriter has the ability to forward the log4j output to a LogFrame class.
     */
    private static class LogWriter extends Writer {
        // Ref to the LogFrame instance that should receive the output
        private LogPanel logpanel;

        /**
         * Constructs a new LogWriter and registers the LogFrame.
         */
        public LogWriter(LogPanel logframe) {
            this.logpanel = logframe;
        }

        /**
         * Method declaration
         *
         *
         * @throws java.io.IOException
         */
        public void close() throws java.io.IOException {
        // TODO: implement this java.io.Writer abstract method
        }

        /**
         * Method declaration
         *
         * @throws java.io.IOException
         */
        public void flush() throws java.io.IOException {
        // TODO: implement this java.io.Writer abstract method
        }

        /**
         * Append 'text' to the LogFrame textarea.
         */
        @Override
        public void write(String text) {
            logpanel.append(text);
        }

        public void write(char[] parm1, int parm2, int parm3) throws java.io.IOException {
            write(String.valueOf(parm1, parm2, parm3));
        }
    }

    /**
     * This method initializes jPanelTools	
     * @return  javax.swing.JPanel
     * @uml.property  name="jPanelTools"
     */
    private JPanel getJPanelTools() {
        if (jPanelTools == null) {
            jLabel = new JLabel();
            jLabel.setText("Logging");
            jPanelTools = new JPanel();
            jPanelTools.add(jLabel, null);
            jPanelTools.add(getJButtonSaveAs(), null);
            jPanelTools.add(getJButtonClear(), null);
            jPanelTools.add(getJCheckBoxAutoScrolling(), null);
            jPanelTools.add(getJCheckBoxLoggingEnabled(), null);
        }
        return jPanelTools;
    }

    /**
     * This method initializes jButtonSaveAs	
     * @return  javax.swing.JButton
     * @uml.property  name="jButtonSaveAs"
     */
    private JButton getJButtonSaveAs() {
        if (jButtonSaveAs == null) {
            jButtonSaveAs = new JButton();
            jButtonSaveAs.setText(Messages.getString("LogPanel.save"));
            jButtonSaveAs.setToolTipText("save log entries in file");
            jButtonSaveAs.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    showSaveFileDialog();
                }
            });
        }
        return jButtonSaveAs;
    }

    /**
     * This method initializes jButtonClear	
     * @return  javax.swing.JButton
     * @uml.property  name="jButtonClear"
     */
    private JButton getJButtonClear() {
        if (jButtonClear == null) {
            jButtonClear = new JButton();
            jButtonClear.setText(Messages.getString("LogPanel.clear"));
            jButtonClear.setToolTipText("clear log entries");
            jButtonClear.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    clear();
                }
            });
        }
        return jButtonClear;
    }

    public void showSaveFileDialog() {
        final JFileChooser chooser = new JFileChooser();
        if (file != null) {
            chooser.setCurrentDirectory(file.getParentFile());
        } else {
            if (suggestedFileName != null) {
                chooser.setSelectedFile(new File(suggestedFileName));
            }
            chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        }
        if (file != null) {
            chooser.setSelectedFile(file);
        }
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        chooser.setMultiSelectionEnabled(false);
        chooser.setDialogTitle("save file");
        final int returnVal = chooser.showSaveDialog(this);
        // hier weiter wenn der modale FileDialog geschlossen wurde
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            if (f.getAbsolutePath().endsWith(".log") == false) {
                f = new File(f.getAbsolutePath() + ".log");
            }
            saveDocument(f);
        }
    }

    public void saveDocument(File file) {
        if (file != null) {
            try {
                if (logger.isDebugEnabled()) {
                    logger.debug("saveDocument(file=" + file + ")");
                }
                BufferedWriter bwFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                String text = jTextArea.getText();
                bwFile.write(text);
                bwFile.close();
                this.file = file;
                changed = false;
            } catch (FileNotFoundException e) {
                logger.error("loadDocument file=" + file + " failed:" + e.getMessage(), e);
            } catch (IOException e) {
                logger.error("loadDocument file=" + file + " failed:" + e.getMessage(), e);
            }
        } else {
            this.file = null;
        }
    }

    /**
     * This method initializes jCheckBoxFreeze	
     * @return  javax.swing.JCheckBox
     * @uml.property  name="jCheckBoxAutoScrolling"
     */
    private JCheckBox getJCheckBoxAutoScrolling() {
        if (jCheckBoxAutoScrolling == null) {
            jCheckBoxAutoScrolling = new JCheckBox();
            jCheckBoxAutoScrolling.setText("auto scrolling");
            jCheckBoxAutoScrolling.setSelected(true);
            jCheckBoxAutoScrolling.setToolTipText("shows always the end of text");
        }
        return jCheckBoxAutoScrolling;
    }

    /**
     * This method initializes jCheckBoxLoggingEnabled	
     * @return  javax.swing.JCheckBox
     * @uml.property  name="jCheckBoxLoggingEnabled"
     */
    private JCheckBox getJCheckBoxLoggingEnabled() {
        if (jCheckBoxLoggingEnabled == null) {
            jCheckBoxLoggingEnabled = new JCheckBox();
            jCheckBoxLoggingEnabled.setText("logging on");
            jCheckBoxLoggingEnabled.setSelected(loggingEnabled);
            jCheckBoxLoggingEnabled.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent e) {
                    loggingEnabled = jCheckBoxLoggingEnabled.isSelected();
                }
            });
        }
        return jCheckBoxLoggingEnabled;
    }

    /**
     * @return  the suggestedFileName
     * @uml.property  name="suggestedFileName"
     */
    public final String getSuggestedFileName() {
        return suggestedFileName;
    }

    /**
     * @param suggestedFileName  the suggestedFileName to set
     * @uml.property  name="suggestedFileName"
     */
    public final void setSuggestedFileName(String suggestedFileName) {
        this.suggestedFileName = suggestedFileName;
        this.file = null;
    }

    /**
     * @return  the changed
     * @uml.property  name="changed"
     */
    public final boolean isChanged() {
        return changed;
    }

} //  @jve:decl-index=0:visual-constraint="10,10"