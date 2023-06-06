import java.io.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.rtf.RTFEditorKit;
import java.awt.*;
import java.awt.event.*;

public class Practice extends JFrame implements FocusListener {

    //Panels
    private JPanel containerpanel = new JPanel();
    private JPanel wordpanel = new JPanel();
    private JPanel leftpanel = new JPanel();
    private JPanel formatpanel = new JPanel();
    private JPanel searchpanel = new JPanel();
    private JPanel textpanel = new JPanel();
    private JPanel rightpanel = new JPanel();
    private JPanel titlepanel = new JPanel();
    private JPanel shapespanel = new JPanel();
    private DrawingPanel drawingpanel = new DrawingPanel();

    JTextPane textArea = new JTextPane();

    //Menu items
    private JMenuBar jmb = new JMenuBar();
    private JMenu file = new JMenu("File");
    private JMenu edit = new JMenu("Edit");
    private JMenu format = new JMenu("Font");
    private JMenu review = new JMenu("Review");
    private JMenu help = new JMenu("Help");

    //JComboBoxes
    JComboBox jComboBox1 = new JComboBox();
    JComboBox fontSizeBox = new JComboBox();

    //Jlabels
    JLabel wordcnt = new JLabel("Word Count: ");
    JLabel wordCount = new JLabel("");
    JLabel charcnt = new JLabel("Character Count: ");
    JLabel characterCount = new JLabel("");
    JLabel informationDisplay = new JLabel("");

    //Listings under menus
    private JMenuItem _new = new JMenuItem("New",new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\new.png"));
    private JMenuItem _open = new JMenuItem("Open",new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\open.png"));
    private JMenuItem _save = new JMenuItem("Save",new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\save.png"));
    private JMenuItem _saveas = new JMenuItem("Save as...",new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\saveas.png"));
    private JMenuItem _exit = new JMenuItem("Exit",new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\exit.png"));
    private JMenuItem _undo = new JMenuItem("Undo",new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\undo.png"));
    private JMenuItem _redo = new JMenuItem("Redo",new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\redo.png"));
    private JMenuItem _cut = new JMenuItem("Cut",new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\cut.png"));
    private JMenuItem _copy = new JMenuItem("Copy",new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\copy.png"));
    private JMenuItem _paste = new JMenuItem("Paste",new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\paste.png"));
    private JMenuItem _find = new JMenuItem("Find",new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\find.png"));
    private JMenuItem _find_and_replace = new JMenuItem("Find and Replace",new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\find_and_replace.png"));
    private JMenuItem _count = new JMenuItem("Word Count",new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\wordcnt.png"));
    private JMenuItem _info = new JMenuItem("Info...");
    private JMenuItem _about_us = new JMenuItem("About Us");

    JTextField findText = new JTextField();
    JTextField replaceWithText = new JTextField();
    JLabel jLabel1 = new JLabel("Find");
    JLabel jLabel2 = new JLabel("Replace");
    JLabel foundInPlaces = new JLabel("");
    JButton jButton4 = new JButton("Replace");
    JButton jButton2 = new JButton("Replace All");
    JButton jButton3 = new JButton("Find Next");
    JButton jButton1 = new JButton("Find All");

    //Submenus
    private JMenu _font = new JMenu("Font");
    private JMenu _size = new JMenu("Size");
    private JMenu _case = new JMenu("Case");

    //Buttons
    private JButton jbtRect = new JButton("Rectangle");
    private JButton jbtOval = new JButton("Oval");
    private JButton jbtline = new JButton("Line");
    private JButton jbtTriangle = new JButton("Triangle");
    private JButton jbtPentagon = new JButton("Pentagon");
    private JButton jbtCLEAR = new JButton("CLEAR");
    private JButton jbtSearch = new JButton("SEARCH");

    //Search field
    private JTextField jtfSearch = new JTextField("Type to search");

    String[] fonts = {"Arial", "Calibri", "Cambria", "Courier New", "Comic Sans MS", "Dialog", "Georgia", "Helevetica", "Lucida Sans", "Monospaced","SignPainter", "Tahoma", "Times New Roman", "Verdana"};
    String[] fontSizes = {"5","10","15","20","25","30","35","40","45","50","55","60","65","70","75","80","85","90"};
    Font newFont = new Font("Times New Roman",Font.PLAIN,20);

    int previousSize = 20;
    String previousFont = "Arial";

    String fn="",dir="",fileName="New Text Document";

    Font startingFont = new Font(previousFont,Font.PLAIN,previousSize);

    static int findNextPos = 0;
    static int oldFindNextPos = 0;
    static int numberOfWordsFound = 0;
    int count=0;
    int currentFileSaved = 0;


    String copiedText = " ";
    StyledDocument document;
    Element element;
    AttributeSet attribute;

    String previousSearchedText = "";

    File openedFile;
    RTFEditorKit editorKit;
    JFileChooser fileChooser = new JFileChooser();

    Highlighter.HighlightPainter myHighlightPainter = new MyHighlighterPainter(Color.yellow);
    Highlighter.HighlightPainter removeTheHighlight = new MyHighlighterPainter(Color.white);

    //removing the highlighter
    int removeHighlights = 0;

    Practice() {
        textArea.setFont(newFont);

        jComboBox1.removeAllItems();

        for(String s :fonts) jComboBox1.addItem(s);

        for(String s:fontSizes) fontSizeBox.addItem(s);

        this.setTitle(fileName + ".rtf");

//Sizes of main menu titles
        file.setPreferredSize(new Dimension(50,25));
        edit.setPreferredSize(new Dimension(50,25));
        format.setPreferredSize(new Dimension(50,25));
        review.setPreferredSize(new Dimension(70,25));
        help.setPreferredSize(new Dimension(50,25));

//Borders on main menu titles
        file.setBorder(BorderFactory.createLineBorder(Color.white));
        edit.setBorder(BorderFactory.createLineBorder(Color.white));
        format.setBorder(BorderFactory.createLineBorder(Color.white));
        review.setBorder(BorderFactory.createLineBorder(Color.white));
        help.setBorder(BorderFactory.createLineBorder(Color.white));

//Adding main menu titles
        jmb.add(file);
        jmb.add(edit);
//        jmb.add(format);
        jmb.add(review);
        jmb.add(help);

//Adding Keyboard Alt Shortcuts
        file.setMnemonic(KeyEvent.VK_F);
        edit.setMnemonic(KeyEvent.VK_E);
        format.setMnemonic(KeyEvent.VK_O);
        review.setMnemonic(KeyEvent.VK_R);
        _new.setMnemonic(KeyEvent.VK_N);
        _open.setMnemonic(KeyEvent.VK_O);
        _save.setMnemonic(KeyEvent.VK_S);
        _saveas.setMnemonic(KeyEvent.VK_A);
        _exit.setMnemonic(KeyEvent.VK_X);
        _undo.setMnemonic(KeyEvent.VK_U);
        _redo.setMnemonic(KeyEvent.VK_R);
        _copy.setMnemonic(KeyEvent.VK_C);
        _cut.setMnemonic(KeyEvent.VK_T);
        _paste.setMnemonic(KeyEvent.VK_P);
        _font.setMnemonic(KeyEvent.VK_F);
        _size.setMnemonic(KeyEvent.VK_S);
        _case.setMnemonic(KeyEvent.VK_C);
        _find.setMnemonic(KeyEvent.VK_F);
        _find_and_replace.setMnemonic(KeyEvent.VK_R);
        _count.setMnemonic(KeyEvent.VK_W);
        help.setMnemonic(KeyEvent.VK_H);
        _info.setMnemonic(KeyEvent.VK_I);
        _about_us.setMnemonic(KeyEvent.VK_B);

        JMenuItem upper = new JMenuItem("UPPER CASE");
        JMenuItem lower = new JMenuItem("lower case");

//Adding Keyboard Action Shortcuts
        _new.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        _open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        _save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        _exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        _undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        _redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        _copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        _cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        _paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        _find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        _info.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,ActionEvent.CTRL_MASK));

//Adding action listeners
        _new.addActionListener(e -> jMenuItem1ActionPerformed(e));

        _open.addActionListener(e -> openFileActionPerformed(e));

        //Save_File
        _save.addActionListener(e -> saveFileActionPerformed(e));

        _saveas.addActionListener(e -> jMenuItem3ActionPerformed(e));

        _exit.addActionListener(e -> System.exit(0));

        _cut.addActionListener((e -> cutEditActionPerformed(e)));

        _copy.addActionListener(e -> copyEditActionPerformed(e));

        _paste.addActionListener(e -> jMenuItem6ActionPerformed(e));

        textArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
//
            }

            @Override
            public void keyPressed(KeyEvent e) {
//
            }

            @Override
            public void keyReleased(KeyEvent e) {
                wordCount();
            }
        });

        jComboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jComboBox1ActionPerformed(e);
            }
        });

        fontSizeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fontSizeBoxActionPerformed(e);
            }
        });

        textArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                wordCount();
            }

            @Override
            public void mousePressed(MouseEvent e) {
//
            }

            @Override
            public void mouseReleased(MouseEvent e) {
//
            }

            @Override
            public void mouseEntered(MouseEvent e) {
//
            }

            @Override
            public void mouseExited(MouseEvent e) {
//
            }
        });



        textArea.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                wordCount();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
//
            }
        });

        upper.addActionListener(e -> toUpperActionPerformed(e));

        lower.addActionListener(e -> toLowerActionPerformed(e));



        jButton1.addActionListener(e -> jButton1ActionPerformed(e));

        jButton2.addActionListener(e -> jButton2ActionPerformed(e));

        jButton3.addActionListener(e -> jButton3ActionPerformed(e));

        jButton4.addActionListener(e -> jButton4ActionPerformed(e));

//Adding menu items under 'File'
        file.add(_new);
        file.add(_open);
        file.addSeparator();
        file.add(_save);
        file.add(_saveas);
        file.addSeparator();
        file.add(_exit);

//Adding menu items under 'Edit'
        edit.add(_cut);
        edit.add(_copy);
        edit.add(_paste);

//Submenu icons
        _font.setIcon(new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\find.png"));
        _size.setIcon(new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\size.png"));
        _case.setIcon(new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\case.png"));

//Font sizes
        JMenuItem font_8 = new JMenuItem("8");
        _size.add(font_8);
        JMenuItem font_9 = new JMenuItem("9");
        _size.add(font_9);
        JMenuItem font_10 = new JMenuItem("10");
        _size.add(font_10);
        JMenuItem font_11 = new JMenuItem("11");
        _size.add(font_11);
        JMenuItem font_12 = new JMenuItem("12");
        _size.add(font_12);
        JMenuItem font_14 = new JMenuItem("14");
        _size.add(font_14);
        JMenuItem font_16 = new JMenuItem("16");
        _size.add(font_16);
        JMenuItem font_18 = new JMenuItem("18");
        _size.add(font_18);
        JMenuItem font_20 = new JMenuItem("20");
        _size.add(font_20);
        JMenuItem font_22 = new JMenuItem("22");
        _size.add(font_22);
        JMenuItem font_24 = new JMenuItem("24");
        _size.add(font_24);
        JMenuItem font_26 = new JMenuItem("26");
        _size.add(font_26);
        JMenuItem font_28 = new JMenuItem("28");
        _size.add(font_28);
        JMenuItem font_36 = new JMenuItem("36");
        _size.add(font_36);
        JMenuItem font_48 = new JMenuItem("48");
        _size.add(font_48);
        JMenuItem font_72 = new JMenuItem("72");
        _size.add(font_72);

//Cases
        _case.add(upper);

        _case.add(lower);

        //Adding menu items under 'Format'
        format.add(_font);
        format.add(_size);
        format.add(_case);

//Adding menu items under 'Review'
//        review.add(_find);
//        review.add(_find_and_replace);
        review.add(_case);
        review.addSeparator();
        review.add(_count);

//Adding menu items under 'Help'
//        help.add(_info);
        help.addSeparator();
        help.add(_about_us);

//Adding Icons for formatting
        ImageIcon bold = new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\bold.png", "Makes Text Bold");
        JToggleButton jbtBold = new JToggleButton(bold);
        ImageIcon italics = new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\italics.png", "Makes Text Italic");
        JToggleButton jbtItalics = new JToggleButton(italics);
        ImageIcon underline = new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\underline.png", "Makes Text underlined");
        JToggleButton jbtUnderline = new JToggleButton(underline);
        ImageIcon strikethrough = new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\strikethrough.png", "Strikes through text");
        JToggleButton jbtStrikethrough = new JToggleButton(strikethrough);
        ImageIcon leftalign = new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\leftalign.png", "Left aligns text");
        JToggleButton jbtLeftalign = new JToggleButton(leftalign);
        ImageIcon rightalign = new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\rightalign.png", "Right aligns text");
        JToggleButton jbtRightalign = new JToggleButton(rightalign);
        ImageIcon centeralign = new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\centeralign.png", "Centre aligns text");
        JToggleButton jbtCenteralign = new JToggleButton(centeralign);
        ImageIcon justify = new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\justify.png", "Justifies text");
        JToggleButton jbtJustify = new JToggleButton(justify);
        ImageIcon increase = new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\increase.png", "Increases font size");
        JButton jbtIncrease = new JButton(increase);
        ImageIcon decrease = new ImageIcon("C:\\Users\\Kabir\\Pictures\\Java Icons\\decrease.png", "Decreases font size");
        JButton jbtDecrease = new JButton(decrease);

//Group of JToggleButtons
        ButtonGroup alignment = new ButtonGroup();
        alignment.add(jbtLeftalign);
        alignment.add(jbtCenteralign);
        alignment.add(jbtRightalign);
        alignment.add(jbtJustify);
        jbtLeftalign.setSelected(true);

//Adding elements into formatpanel
        formatpanel.setLayout(new BoxLayout(formatpanel,BoxLayout.LINE_AXIS));
        formatpanel.add(jbtBold);
        formatpanel.add(jbtItalics);
        formatpanel.add(jbtUnderline);
        formatpanel.add(jbtStrikethrough);
        formatpanel.add(Box.createRigidArea(new Dimension(5,0)));
        formatpanel.add(jbtLeftalign);
        formatpanel.add(jbtCenteralign);
        formatpanel.add(jbtRightalign);
        formatpanel.add(jbtJustify);
        formatpanel.add(Box.createRigidArea(new Dimension(5,0)));
//        formatpanel.add(jbtIncrease);
//        formatpanel.add(jbtDecrease);
        formatpanel.add(Box.createRigidArea(new Dimension(10,0)));
        formatpanel.add(jComboBox1);
        formatpanel.add(fontSizeBox);

//Action Listeners
        jbtBold.addActionListener(e -> makeTextBold(e));

        jbtItalics.addActionListener(e -> makeTextItalics(e));

        jbtUnderline.addActionListener(e -> makeTextUnderlined(e));

        jbtStrikethrough.addActionListener(e -> jMenuItem5ActionPerformed(e));

        _about_us.addActionListener(e -> jMenuItem12ActionPerformed(e));

//Set layouts
        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new BoxLayout(mainpanel,BoxLayout.LINE_AXIS));
        leftpanel.setLayout(new BoxLayout(leftpanel,BoxLayout.PAGE_AXIS));
        rightpanel.setLayout(new BoxLayout(rightpanel,BoxLayout.PAGE_AXIS));
        containerpanel.setLayout(new BoxLayout(containerpanel,BoxLayout.PAGE_AXIS));

//Set alignments
        formatpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        textpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftpanel.setAlignmentX(Component.LEFT_ALIGNMENT);

//Elements of searchpanel


        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
//        jtfSearch.setPreferredSize(new Dimension(400,30));
//        searchpanel.add(Box.createRigidArea(new Dimension(5,0)));
//        searchpanel.add(jtfSearch);
//        jtfSearch.addFocusListener(this);
//        searchpanel.add(Box.createRigidArea(new Dimension(5,0)));
//        searchpanel.add(jbtSearch);
//        searchpanel.add(Box.createRigidArea(new Dimension(5,0)));
        findText.setPreferredSize(new Dimension(300,15));
        replaceWithText.setPreferredSize(new Dimension(300,15));
        searchpanel.setLayout(new BoxLayout(searchpanel,BoxLayout.PAGE_AXIS));
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
        panel1.add(jLabel1);
//        panel1.add(Box.createRigidArea(new Dimension(0,5)));
        panel1.add(findText);
//        panel1.add(Box.createRigidArea(new Dimension(0,5)));
        panel1.add(jLabel2);
//        panel1.add(Box.createRigidArea(new Dimension(0,5)));
        panel1.add(replaceWithText);
//        panel1.add(Box.createRigidArea(new Dimension(0,5)));
        searchpanel.add(panel1);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.LINE_AXIS));
        panel2.add(jButton1);
        panel2.add(Box.createRigidArea(new Dimension(10,0)));
        panel2.add(jButton3);
        panel2.add(Box.createRigidArea(new Dimension(10,0)));
        panel2.add(jButton4);
        panel2.add(Box.createRigidArea(new Dimension(10,0)));
        panel2.add(jButton2);
        searchpanel.add(panel2);
        searchpanel.add(foundInPlaces);

//Elements of textpanel

        textArea.setPreferredSize(new Dimension(500,300));
        textpanel.setLayout(new BoxLayout(textpanel,BoxLayout.LINE_AXIS));
        textpanel.add(textArea);

//Elements of wordpanel

        wordpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        wordpanel.add(wordcnt);
        wordpanel.add(wordCount);
        wordpanel.add(charcnt);
        wordpanel.add(characterCount);
        wordpanel.add(informationDisplay);
        wordpanel.setBackground(Color.LIGHT_GRAY);

//Elements of leftpanel
        leftpanel.add(Box.createRigidArea(new Dimension(0,10)));
        leftpanel.add(formatpanel);
        leftpanel.add(Box.createRigidArea(new Dimension(0,10)));
        leftpanel.add(searchpanel);
        leftpanel.add(Box.createRigidArea(new Dimension(0,10)));
        leftpanel.add(textpanel);
        leftpanel.setBorder(BorderFactory.createLineBorder(Color.black));

//Elements of shapespanel
        shapespanel.setLayout(new BoxLayout(shapespanel,BoxLayout.LINE_AXIS));

        shapespanel.add(jbtRect);
        jbtRect.addActionListener(e -> drawingpanel.rectangle());

        shapespanel.add(jbtOval);
        jbtOval.addActionListener(e -> drawingpanel.oval());

        shapespanel.add(jbtline);
        jbtline.addActionListener(e -> drawingpanel.line());

        shapespanel.add(jbtTriangle);
        jbtTriangle.addActionListener(e -> drawingpanel.triangle());

        jbtLeftalign.addActionListener(e -> jMenuItem2ActionPerformed(e));

        jbtRightalign.addActionListener(e -> rightAlignActionPerformed(e));

        jbtJustify.addActionListener(e -> justifyAlignActionPerformed(e));

        jbtCenteralign.addActionListener(e -> centreAlignActionPerformed(e));

        shapespanel.add(jbtPentagon);
        jbtPentagon.addActionListener(e -> drawingpanel.pentagon());

//Leave space
        shapespanel.add(Box.createRigidArea(new Dimension(5,0)));

        shapespanel.add(jbtCLEAR);
        jbtCLEAR.addActionListener(e -> drawingpanel.clear());

//Elements of drawingpanel
        drawingpanel.setSize(200,300);
        drawingpanel.setBackground(Color.LIGHT_GRAY);

//Elements of rightpanel
        rightpanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightpanel.add(new JLabel("SketchPad"));
        rightpanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightpanel.add(shapespanel);
        rightpanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightpanel.add(drawingpanel);
        rightpanel.setBorder(BorderFactory.createLineBorder(Color.black));

//Elements of mainpanel
        mainpanel.add(leftpanel);
        mainpanel.add(Box.createRigidArea(new Dimension(10,0)));
        mainpanel.add(rightpanel);

//Elements of containerpanel
        this.add(jmb,BorderLayout.NORTH);
        containerpanel.add(mainpanel);
        containerpanel.add(Box.createVerticalGlue());
        containerpanel.add(wordpanel);

//Add containerpanel to JFrame
        this.add(containerpanel);
    }

    public static void main(String[] args) {
        JFrame frame = new Practice();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(75,75);
        frame.setSize(1250,600);
        frame.setTitle("Text Editor");
//        frame.setResizable(false);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(jtfSearch.getText().toLowerCase().equals("type to search"))
            jtfSearch.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(jtfSearch.getText().equals("")) jtfSearch.setText("Type to search");
    }

    public static class DrawingPanel extends JPanel {
        final int X=5;      //Radius of circles
        private boolean rect=false, oval=false, line=false, triangle=false, pentagon=false;  //Used in paintComponent
        int r_x1,r_y1,r_w,r_h;                                                              //Coordinates for rectangle and oval
        int l_x1,l_x2,l_y1,l_y2;                                                            //Coordinates for line
        int t_x1, t_y1, t_x2, t_y2, t_x3, t_y3;                                             //Coordinates for triangle
        int p_x1, p_x2, p_x3, p_x4, p_x5, p_y1, p_y2, p_y3, p_y4, p_y5;                     //Coordinates for pentagon

        public void rectangle() {
            r_x1 = 100;
            r_y1 = 150;
            r_w = 150;
            r_h = 100;
            rect = true;
            oval = false;
            line = false;
            triangle = false;
            pentagon = false;
            repaint();
            this.addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    dragmouse(e);
                }

                @Override
                public void mouseMoved(MouseEvent e) {
//Do nothing
                }
            });
        }

        public void oval() {
            r_x1=200;
            r_y1=150;
            r_w=100;
            r_h=200;
            rect=false;
            line = false;
            oval=true;
            pentagon=false;
            triangle=false;
            repaint();
            this.addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    dragmouse(e);
                }

                @Override
                public void mouseMoved(MouseEvent e) {
//Do nothing
                }
            });
        }

        public void line() {
            l_x1=100;
            l_y1=200;
            l_x2=200;
            l_y2=150;
            rect=false;
            line = true;
            oval=false;
            pentagon=false;
            triangle=false;
            repaint();
            this.addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    dragmouse(e);
                }
                @Override
                public void mouseMoved(MouseEvent e) {
//Do nothing
                }
            });
        }

        public void triangle() {
            t_x1=110;
            t_y1=110;
            t_x2=100;
            t_y2=200;
            t_x3=200;
            t_y3=190;
            rect=false;
            line = false;
            oval=false;
            pentagon=false;
            triangle=true;
            repaint();
            this.addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    dragmouse(e);
                }
                @Override
                public void mouseMoved(MouseEvent e) {
//Do nothing
                }
            });
        }

        public void pentagon() {
            p_x1=150;
            p_y1=100;
            p_x2=100;
            p_x3=120;
            p_x4=180;
            p_x5=200;
            p_y2=140;
            p_y5=140;
            p_y3=180;
            p_y4=180;
            rect=false;
            line = false;
            oval=false;
            pentagon=true;
            triangle=false;
            repaint();
            this.addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    dragmouse(e);
                }

                @Override
                public void mouseMoved(MouseEvent e) {
//Do nothing
                }
            });
        }

        public void clear() {
            rect=false;
            line = false;
            oval=false;
            pentagon=false;
            triangle=false;
            repaint();
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(rect==true) {
                g.fillRect(r_x1, r_y1, r_w, r_h);
                g.setColor(Color.white);
                g.fillOval(r_x1 - X, r_y1 - X, 2*X, 2*X);
                g.fillOval(r_x1 +r_w - X, r_y1 - X, 2*X, 2*X);
                g.fillOval(r_x1 - X, r_y1 +r_h - X, 2*X, 2*X);
                g.fillOval(r_x1 +r_w- X, r_y1 +r_h- X, 2*X, 2*X);
                g.setColor(Color.BLUE);
                g.fillOval(r_x1+r_w/2-X,r_y1+r_h/2-X,2*X,2*X);
            }
            else if(oval==true) {
                g.setColor(Color.CYAN);
                g.fillOval(r_x1,r_y1,r_w,r_h);
                g.setColor(Color.white);
                g.fillOval(r_x1 - X, r_y1 - X, 2*X, 2*X);
                g.fillOval(r_x1 +r_w - X, r_y1 - X, 2*X, 2*X);
                g.fillOval(r_x1 - X, r_y1 +r_h - X, 2*X, 2*X);
                g.fillOval(r_x1 +r_w- X, r_y1 +r_h- X, 2*X, 2*X);
                g.setColor(Color.RED);
                g.fillOval(r_x1+r_w/2-X,r_y1+r_h/2-X,2*X,2*X);
            }

            else if(line==true) {
                Graphics2D g2 = (Graphics2D) g;
                ((Graphics2D) g).setStroke(new BasicStroke(X));
                g.setColor(Color.GREEN);
                g.drawLine(l_x1,l_y1,l_x2,l_y2);
                g.setColor(Color.white);
                g.fillOval(l_x1 - X, l_y1 - X, 2*X, 2*X);
                g.fillOval(l_x2 - X, l_y2 - X, 2*X, 2*X);
                g.setColor(Color.black);
                g.fillOval((l_x1+l_x2)/2-X,(l_y1+l_y2)/2-X,2*X,2*X);
            }

            else if(triangle==true) {
                g.setColor(Color.orange);
                int[] xpts = {t_x1,t_x2,t_x3};
                int[] ypts = {t_y1,t_y2,t_y3};
                g.fillPolygon(xpts,ypts,3);
                g.setColor(Color.white);
                g.fillOval((t_x1+t_x2+t_x3)/3-X,(t_y1+t_y2+t_y3)/3-X,2*X,2*X);
                g.setColor(Color.YELLOW);
                g.fillOval(t_x1-X, t_y1-X,2*X,2*X);
                g.fillOval(t_x2-X, t_y2-X,2*X,2*X);
                g.fillOval(t_x3-X, t_y3-X,2*X,2*X);
            }

            else if(pentagon==true) {
                g.setColor(Color.YELLOW);
                int[] xpts = {p_x1,p_x2,p_x3,p_x4,p_x5};
                int[] ypts = {p_y1,p_y2,p_y3,p_y4,p_y5};
                g.fillPolygon(xpts,ypts,5);
                g.setColor(Color.orange);
                g.fillOval(p_x1-X, p_y1-X,2*X,2*X);
                g.fillOval(p_x2-X, p_y2-X,2*X,2*X);
                g.fillOval(p_x3-X, p_y3-X,2*X,2*X);
                g.fillOval(p_x4-X, p_y4-X,2*X,2*X);
                g.fillOval(p_x5-X, p_y5-X,2*X,2*X);
                g.setColor(Color.black);
                g.fillOval((p_x1+p_x2+p_x3+p_x4+p_x5)/5-X,(p_y1+p_y2+p_y3+p_y4+p_y5)/5-X, 2*X, 2*X);
            }
        }

        public void dragmouse(MouseEvent e) {
            if(rect==true||oval==true) {
                if (e.getX() <= r_x1+r_w/2 + X && e.getX() >= r_x1+r_w/2 - X && e.getY() <= r_y1+r_h/2 + X && e.getY() >= r_y1+r_h/2 - X) {
                    r_x1 = e.getX() - r_w / 2;
                    r_y1 = e.getY() - r_h / 2;
                    repaint();
                }

//Bottom Right
                if (e.getX() <= r_x1 + r_w + X &&
                        e.getX() >= r_x1 + r_w - X &&
                        e.getY() <= r_y1 + r_h + X &&
                        e.getY() >= r_y1 + r_h - X) {
                    r_w = e.getX() - r_x1;
                    r_h = e.getY() - r_y1;
                    if (r_w <4 * X) r_w = 4 * X;
                    if (r_h <4 * X) r_h = 4 * X;
                    repaint();
                }

//Top Left
                if (e.getX() <= r_x1 + X &&
                        e.getX() >= r_x1 - X &&
                        e.getY() <= r_y1 + X &&
                        e.getY() >= r_y1 - X) {
                    final int r_x2 = r_x1 + r_w;
                    final int r_y2 = r_y1 + r_h;
                    r_x1 = e.getX();
                    r_y1 = e.getY();
                    r_w = r_x2 - r_x1;
                    r_h = r_y2 - r_y1;
                    if (r_w <4 * X) r_w = 4 * X;
                    if (r_h <4 * X) r_h = 4 * X;
                    repaint();
                }

//Top Right
                if (e.getX() <= r_x1 + r_w + X &&
                        e.getX() >= r_x1 + r_w - X &&
                        e.getY() <= r_y1 + X &&
                        e.getY() >= r_y1 - X) {
                    final int r_y2 = r_y1 + r_h;
                    r_w = e.getX() - r_x1;
                    r_y1 = e.getY();
                    r_h = r_y2 - r_y1;
                    if (r_w <4 * X) r_w = 4 * X;
                    if (r_h <4 * X) r_h = 4 * X;
                    repaint();
                }

//Bottom Left
                if (e.getX() <= r_x1 + X &&
                        e.getX() >= r_x1 - X &&
                        e.getY() <= r_y1 + r_h + X &&
                        e.getY() >= r_y1 + r_h - X) {
                    final int r_x2 = r_x1 + r_w;
                    r_h = e.getY() - r_y1;
                    r_x1 = e.getX();
                    r_w = r_x2 - r_x1;
                    if (r_w <4 * X) r_w = 4 * X;
                    if (r_h <4 * X) r_h = 4 * X;
                    repaint();
                }
            }

            else if(line) {
//Centre
                if(e.getX()<=(l_x1+l_x2)/2+X &&
                        e.getX()>=(l_x1+l_x2)/2-X &&
                        e.getY()<=(l_y1+l_y2)/2+X &&
                        e.getY()>=(l_y1+l_y2)/2-X) {
                    final int x = (l_x1+l_x2)/2;
                    final int y = (l_y1+l_y2)/2;
                    l_x1 += e.getX()-x;
                    l_y1 += e.getY()-y;
                    l_x2 += e.getX()-x;
                    l_y2 += e.getY()-y;
                    repaint();
                }

//First end
                else if(e.getX()<=l_x1+X &&
                        e.getX()>=l_x1-X &&
                        e.getY()<=l_y1+X &&
                        e.getY()>=l_y1-X) {
                    l_x1 = e.getX();
                    l_y1 = e.getY();
                    repaint();
                }

//Second end
                else if(e.getX()<=l_x2+X &&
                        e.getX()>=l_x2-X &&
                        e.getY()<=l_y2+X &&
                        e.getY()>=l_y2-X) {
                    l_x2 = e.getX();
                    l_y2 = e.getY();
                    repaint();
                }
            }

            else if(triangle==true) {
//Centre
                if(e.getX()<=(t_x1+t_x2+t_x3)/3+X &&
                        e.getX()>=(t_x1+t_x2+t_x3)/3-X &&
                        e.getY()<=(t_y1+t_y2+t_y3)/3+X &&
                        e.getY()>=(t_y1+t_y2+t_y3)/3-X) {
                    final int x = (t_x1+t_x2+t_x3)/3;
                    final int y = (t_y1+t_y2+t_y3)/3;
                    t_x1 += e.getX()-x;
                    t_y1 += e.getY()-y;
                    t_x2 += e.getX()-x;
                    t_y2 += e.getY()-y;
                    t_x3 += e.getX()-x;
                    t_y3 += e.getY()-y;
                    repaint();
                }
//Vertex 1
                else if(e.getX()>=t_x1-X&&e.getX()<=t_x1+X&&e.getY()>=t_y1-X&e.getY()<=t_y1+X) {
                    t_x1=e.getX();
                    t_y1=e.getY();
                    repaint();
                }
//Vertex 2
                else if(e.getX()>=t_x2-X&&e.getX()<=t_x2+X&&e.getY()>=t_y2-X&&e.getY()<=t_y2+X) {
                    t_x2=e.getX();
                    t_y2=e.getY();
                    repaint();
                }
//Vertex 3
                else if(e.getX()>=t_x3-X&&e.getX()<=t_x3+X&&e.getY()>=t_y3-X&&e.getY()<=t_y3+X) {
                    t_x3=e.getX();
                    t_y3=e.getY();
                    repaint();
                }
            }
            else if(pentagon==true) {
//Centre
                if(e.getX()<=(p_x1+p_x2+p_x3+p_x4+p_x5)/5+X&&e.getX()>=(p_x1+p_x2+p_x3+p_x4+p_x5)/5-X&&e.getY()>=(p_y1+p_y2+p_y3+p_y4+p_y5)/5-X&&e.getY()<=(p_y1+p_y2+p_y3+p_y4+p_y5)/5+X) {
                    final int x = (p_x1+p_x2+p_x3+p_x4+p_x5)/5;
                    final int y = (p_y1+p_y2+p_y3+p_y4+p_y5)/5;
                    p_x1 += e.getX()-x;
                    p_y1 += e.getY()-y;
                    p_x2 += e.getX()-x;
                    p_y2 += e.getY()-y;
                    p_x3 += e.getX()-x;
                    p_y3 += e.getY()-y;
                    p_x4 += e.getX()-x;
                    p_y4 += e.getY()-y;
                    p_x5 += e.getX()-x;
                    p_y5 += e.getY()-y;
                    repaint();
                }
//Vertex 1
                else if(e.getX()<=p_x1+X&e.getX()>=p_x1-X&&e.getY()>=p_y1-X&&e.getY()<=p_y1+X) {
                    p_x1=e.getX();
                    p_y1=e.getY();
                    repaint();
                }
//Vertex 2
                else if(e.getX()<=p_x2+X&e.getX()>=p_x2-X&&e.getY()>=p_y2-X&&e.getY()<=p_y2+X) {
                    p_x2=e.getX();
                    p_y2=e.getY();
                    repaint();
                }
//Vertex 3
                else if(e.getX()<=p_x3+X&e.getX()>=p_x3-X&&e.getY()>=p_y3-X&&e.getY()<=p_y3+X) {
                    p_x3=e.getX();
                    p_y3=e.getY();
                    repaint();
                }
//Vertex 4
                else if(e.getX()<=p_x4+X&e.getX()>=p_x4-X&&e.getY()>=p_y4-X&&e.getY()<=p_y4+X) {
                    p_x4=e.getX();
                    p_y4=e.getY();
                    repaint();
                }
//Vertex 5
                else if(e.getX()<=p_x5+X&e.getX()>=p_x5-X&&e.getY()>=p_y5-X&&e.getY()<=p_y5+X) {
                    p_x5=e.getX();
                    p_y5=e.getY();
                    repaint();
                }
            }
        }
    }

    private void saveFileActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
//Save_File
        System.out.println("Saving file");
        int flag = fileChooser.showSaveDialog(null);
        if (flag == JFileChooser.APPROVE_OPTION) {

            File saveFiles = fileChooser.getSelectedFile();
            StyledDocument documents = textArea.getStyledDocument();
            RTFEditorKit editorKits = new RTFEditorKit();
            BufferedOutputStream outputStream;
            try {
                outputStream = new BufferedOutputStream(new FileOutputStream(saveFiles));
                editorKits.write(outputStream, documents, documents.getStartPosition().getOffset(), documents.getLength());
                outputStream.flush();
                outputStream.close();
            } catch (BadLocationException e1) {
                e1.getCause();
            } catch (IOException e1) {
                e1.getSuppressed();
            }

            fileName = fileChooser.getName(saveFiles);
            this.setTitle(fileName + ".rtf");
            currentFileSaved=1;
        }
    }

    private void openFileActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:

        JFileChooser fileChooser = new JFileChooser();
        textArea.setText(" ");
        fileChooser.showOpenDialog(null);
        openedFile = fileChooser.getSelectedFile();
        RTFEditorKit editorKit = new RTFEditorKit();
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(openedFile);
            editorKit.read(inputStream,textArea.getStyledDocument(), 0);
            inputStream.close();
        } catch (BadLocationException e1) {
            e1.getCause();
        } catch (IOException e2) {
            e2.printStackTrace();
        }

    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
//NEW FILE
        if(currentFileSaved==0){
//call the save doc function
            this.saveFileActionPerformed(evt);
        }else{
            this.setTitle("New Text Document");
            textArea.setText(" ");
            textArea.setFont(newFont);
            currentFileSaved=0;
        }
    }

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:

//SAVE_AS
        System.out.println("Save As");

        int flag = fileChooser.showSaveDialog(null);
        if (flag == JFileChooser.APPROVE_OPTION) {

            File saveFiles = fileChooser.getSelectedFile();
            StyledDocument documents = textArea.getStyledDocument();
            RTFEditorKit editorKits = new RTFEditorKit();
            BufferedOutputStream outputStream;
            try {
                outputStream = new BufferedOutputStream(new FileOutputStream(saveFiles));
                editorKits.write(outputStream, documents, documents.getStartPosition().getOffset(), documents.getLength());
                outputStream.flush();
                outputStream.close();
            } catch (BadLocationException e1) {
                e1.getCause();
            } catch (IOException e1) {
                e1.getSuppressed();
            }
            fileName = fileChooser.getName(saveFiles);
            this.setTitle(fileName + ".rtf");
            currentFileSaved=1;

        }

    }

    private void cutEditActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
        document = (StyledDocument) textArea.getDocument();
        int selectionEnd = textArea.getSelectionEnd();
        int selectionStart = textArea.getSelectionStart();

        element = document.getCharacterElement(selectionStart);
        attribute = element.getAttributes();

        copiedText = textArea.getSelectedText();

        try {
            System.out.println("CUT");
            document.remove(selectionStart, textArea.getSelectedText().length());
        } catch (BadLocationException ex) {
            Logger.getLogger(Practice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void copyEditActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:

        System.out.println("COPY");
        document = (StyledDocument) textArea.getDocument();
        int selectionEnd = textArea.getSelectionEnd();
        int selectionStart = textArea.getSelectionStart();

        element = document.getCharacterElement(selectionStart);
        attribute = element.getAttributes();

        copiedText = textArea.getSelectedText();

    }

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            System.out.println("PASTE");
            document.insertString(textArea.getCaretPosition(), " " + copiedText + " ", attribute);
        } catch (BadLocationException ex) {
            Logger.getLogger(Practice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void makeTextBold(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
        System.out.println("Making Text Bold");

        StyledDocument doc = (StyledDocument) textArea.getDocument();
        int selectionEnd = textArea.getSelectionEnd();
        int selectionStart = textArea.getSelectionStart();

        Element element = doc.getCharacterElement(selectionStart);
        AttributeSet as =  element.getAttributes();

        if(selectionEnd == selectionStart)return;

        MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
        StyleConstants.setBold(asNew, !StyleConstants.isBold(as));
        doc.setCharacterAttributes(selectionStart, textArea.getSelectedText()
                .length(), asNew, true);
    }
    private void makeTextItalics(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
        System.out.println("Making Text Italics");

        StyledDocument doc = (StyledDocument) textArea.getDocument();
        int selectionEnd = textArea.getSelectionEnd();
        int selectionStart = textArea.getSelectionStart();

        Element element = doc.getCharacterElement(selectionStart);
        AttributeSet as =  element.getAttributes();

        if(selectionEnd == selectionStart)return;

        MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
        StyleConstants.setItalic(asNew, !StyleConstants.isItalic(as));
        doc.setCharacterAttributes(selectionStart, textArea.getSelectedText()
                .length(), asNew, true);
    }

    private void makeTextUnderlined(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
        System.out.println("Making Text Italics");

        StyledDocument doc = (StyledDocument) textArea.getDocument();
        int selectionEnd = textArea.getSelectionEnd();
        int selectionStart = textArea.getSelectionStart();

        Element element = doc.getCharacterElement(selectionStart);
        AttributeSet as =  element.getAttributes();

        if(selectionEnd == selectionStart) return;

        MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
        StyleConstants.setUnderline(asNew, !StyleConstants.isUnderline(as));
        doc.setCharacterAttributes(selectionStart, textArea.getSelectedText()
                .length(), asNew, true);
    }

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:

        System.out.println("Strikethrough");

        StyledDocument doc = (StyledDocument) textArea.getDocument();
        int selectionEnd = textArea.getSelectionEnd();
        int selectionStart = textArea.getSelectionStart();

        Element element = doc.getCharacterElement(selectionStart);
        AttributeSet as =  element.getAttributes();

        if(selectionEnd == selectionStart) return;

        MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
        StyleConstants.setStrikeThrough(asNew, !StyleConstants.isStrikeThrough(as));
        doc.setCharacterAttributes(selectionStart, textArea.getSelectedText()
                .length(), asNew, true);
    }

    public void wordCount(){
        String s;
        int counter=0;

        if(textArea.getSelectedText()==null)s = textArea.getText();
        else{
            s = textArea.getSelectedText();
            counter=1;
        }

//Variables declaration
        int whiteSpaces = 0;
        int words = 0;
        int characters = 0;

        int flag=0;
        if(s.isEmpty()){
            words=0;
            characters=0;
        }else{
            String[] array = s.split(" ");
            for(String d :array){

                String[] newLine = d.split("\n");
                if(newLine.length != 1){
                    flag=1;
                    for(String line:newLine){
                        if(!line.equals(""))words++;
                        characters = characters + line.length();
                    }
                }

                if(flag==0){
                    words++;
                    characters = characters + d.length();
                }
                flag=0;
            }
        }

        characterCount.setText(String.valueOf(characters));
        wordCount.setText(String.valueOf(words));
        if(counter==1) informationDisplay.setText("WORD COUNT OF SELECTED TEXT ONLY");
        else informationDisplay.setText("WORD COUNT OF ENTIRE DOCUMENT");
    }

    private void textAreaMouseClicked(java.awt.event.MouseEvent evt) {
// TODO add your handling code here:
        wordCount();
    }

    private void textAreaKeyReleased(java.awt.event.KeyEvent evt) {
// TODO add your handling code here:
        wordCount();
    }

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:

//fontsSelectionsList
        System.out.println("Setting font to "+jComboBox1.getSelectedItem());

        String selectedFont = (String)jComboBox1.getSelectedItem();
        Font f = new Font(selectedFont,Font.PLAIN,previousSize);

        if(textArea.getSelectedText()==null)textArea.setFont(f);
            else{
            StyledDocument doc = (StyledDocument) textArea.getDocument();
            int selectionEnd = textArea.getSelectionEnd();
            int selectionStart = textArea.getSelectionStart();

            Element elements = doc.getCharacterElement(selectionStart);
            AttributeSet as =  elements.getAttributes();

            MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
            StyleConstants.setFontFamily(asNew, selectedFont);
            doc.setCharacterAttributes(selectionStart, textArea.getSelectedText()
                    .length(), asNew, true);
        }
        previousFont = selectedFont;
    }

    private void fontSizeBoxActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
//        //fontsSizeList
        System.out.println("Setting font to "+jComboBox1.getSelectedItem());

        String s = fontSizeBox.getSelectedItem().toString();
        int selectedSize = Integer.valueOf(s);

        StyledDocument doc = (StyledDocument) textArea.getDocument();
        int selectionEnd = textArea.getSelectionEnd();
        int selectionStart = textArea.getSelectionStart();

        Element element = doc.getCharacterElement(selectionStart);
        AttributeSet as =  element.getAttributes();

        if(selectionEnd == selectionStart){
            Font newFonts = new Font(previousFont,Font.PLAIN,selectedSize);
            textArea.setFont(newFonts);
            return;
        }

        MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
        StyleConstants.setFontSize(asNew, selectedSize);
        doc.setCharacterAttributes(selectionStart, textArea.getSelectedText()
                .length(), asNew, true);

        previousSize = selectedSize;
    }

    public void highlight(JTextComponent textComponent, String s, Highlighter.HighlightPainter colourChoice){
        try{
            Highlighter h = textComponent.getHighlighter();
            Document doc = textComponent.getDocument();
            String text = doc.getText(0, doc.getLength());
            int pos = 0;

            while((pos = text.toUpperCase().indexOf(s.toUpperCase(),pos))>=0){
                h.addHighlight(pos, pos+s.length(), colourChoice);
                pos += s.length();
            }


        }catch(BadLocationException e){
            System.out.println(e.getMessage());
        }
    }

    public static void findNextHighlight(JTextComponent textComponent, String s, Highlighter.HighlightPainter colourChoice){
        try{
            Highlighter h = textComponent.getHighlighter();
            Document doc = textComponent.getDocument();
            String text = doc.getText(0, doc.getLength());
            if((findNextPos = text.toUpperCase().indexOf(s.toUpperCase(),findNextPos))>=0){
                System.out.println("Inside findNextHighlight\nFINDNEXTPOS : " + findNextPos + "\n");
                h.addHighlight(findNextPos, findNextPos+s.length(), colourChoice);
                findNextPos += s.length();
            }
        }catch(BadLocationException e){
            System.out.println(e.getMessage());
        }
    }

    public void removeHighlighter(JTextComponent textField, int remove){

//If remove is 1, all the highlights are removed, but if remove is 0 then the highlights are removed one by one

        Highlighter highlite = textArea.getHighlighter();
        Highlighter.Highlight[] highlites = highlite.getHighlights();

        if(remove==1)
            for (int i = 0; i < highlites.length; i++)
                if (highlites[i].getPainter() instanceof MyHighlighterPainter)
                    highlite.removeHighlight(highlites[i]);
    }

    //FindAll
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:

//Find All - highlights all the text that match the searched text

        String s = textArea.getText();
        if(count>0){
            count=0;
            removeHighlighter(textArea,1);
        }

        if(s.isEmpty()) JOptionPane.showMessageDialog(null, "Please enter the word to be searched");
        else{
            String[] array = s.split(" ");
            for(String arrayOne:array){
                if(arrayOne.contains("\n")){
                    String[] newLineArray = arrayOne.split("\n");
                    for(String newLineSearch: newLineArray)
                        if(newLineSearch.contains(findText.getText()))
                            count++;
                }else
                    if(arrayOne.contains(findText.getText()))count++;
            }

        }

        foundInPlaces.setText("Found in " + count + " places");

        highlight(textArea,findText.getText(),myHighlightPainter);

    }

    //ReplaceOneByOne
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:

//Replace one by one

        StyledDocument doc = (StyledDocument) textArea.getDocument();
        int pos = findNextPos - findText.getText().length();
        try {
            System.out.println("Inside replace -> pos : " + pos + "\n FIND NEXT POS : " + findNextPos);

            Element element = doc.getCharacterElement(pos);
            AttributeSet as =  element.getAttributes();
            doc.remove(pos, findText.getText().length());
            doc.insertString(pos, replaceWithText.getText(), as);

//                pos+= replaceWithText.getText().length();
        } catch (BadLocationException ex) {
            Logger.getLogger(Practice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //findNext
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:

        if (findText.getText().equals(previousSearchedText)) {
            previousSearchedText = findText.getText();
            findNextPos = 0;
        }

        if (findNextPos == 0) {
            System.out.println("FindNextPos : " + findNextPos + "\n");
            findNextHighlight(textArea, findText.getText(), myHighlightPainter);
        } else {
            try {

                System.out.println("FindNextPos : " + findNextPos);
                System.out.println("OldFindNextPos : " + oldFindNextPos + "\n");

                Highlighter h = textArea.getHighlighter();
                Document doc = textArea.getDocument();
                String text = doc.getText(0, doc.getLength());

                removeHighlighter(textArea, 1);
                findNextHighlight(textArea, findText.getText(), myHighlightPainter);
                oldFindNextPos = findNextPos;
            } catch (BadLocationException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //ReplaceAll
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt){
// TODO add your handling code here:

//removes the highlights from the selected texts
        removeHighlighter(textArea,1);

//replacing the found text with the user entered text

        StyledDocument doc = (StyledDocument) textArea.getDocument();
        int pos=0;
        try {
            while((pos = textArea.getText().toUpperCase().indexOf(findText.getText().toUpperCase(), pos))>=0){
                System.out.println(pos);

                Element element = doc.getCharacterElement(pos);
                AttributeSet as =  element.getAttributes();
                doc.remove(pos, findText.getText().length());
                doc.insertString(pos, replaceWithText.getText(), as);

                pos+= replaceWithText.getText().length();
            }
        } catch (BadLocationException ex) {
            Logger.getLogger(Practice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
//About_Help
        JOptionPane.showMessageDialog(null, "Created by \nMohith S\nKabir Arora");
    }

    private void toLowerActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:

        System.out.println("Making Text LOWERCASE");

        String s = textArea.getSelectedText().toUpperCase();

        StyledDocument doc = (StyledDocument) textArea.getStyledDocument();
        int selectionEnd = textArea.getSelectionEnd();
        int selectionStart = textArea.getSelectionStart();

        Element elements = doc.getCharacterElement(selectionStart);
        AttributeSet as =  elements.getAttributes();

        if(selectionEnd == selectionStart)return;
        try {
            doc.remove(selectionStart, s.length());
            doc.insertString(selectionStart, s.toLowerCase(), as);
        } catch (BadLocationException ex) {
            Logger.getLogger(Practice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void toUpperActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:

        System.out.println("Making Text UPPERCASE");

        String s = textArea.getSelectedText().toUpperCase();

        StyledDocument doc = (StyledDocument) textArea.getStyledDocument();
        int selectionEnd = textArea.getSelectionEnd();
        int selectionStart = textArea.getSelectionStart();

        Element elements = doc.getCharacterElement(selectionStart);
        AttributeSet as =  elements.getAttributes();

        try {
            doc.remove(selectionStart, s.length());
            doc.insertString(selectionStart, s.toUpperCase(), as);
        } catch (BadLocationException ex) {
            Logger.getLogger(Practice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void centreAlignActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:

        System.out.println("Aligning text to CENTER");

        String s = textArea.getText();
        textArea.setText("");

        StyledDocument doc = textArea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        try {
            doc.insertString(0, s, center);
        } catch (BadLocationException ex) {
            Logger.getLogger(Practice.class.getName()).log(Level.SEVERE, null, ex);
        }}

    private void justifyAlignActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:

        System.out.println("Aligning text to JUSTIFY");

        String s = textArea.getText();
        textArea.setText("");

        StyledDocument doc = textArea.getStyledDocument();
        SimpleAttributeSet justify = new SimpleAttributeSet();
        StyleConstants.setAlignment(justify, StyleConstants.ALIGN_JUSTIFIED);
        doc.setParagraphAttributes(0, doc.getLength(), justify, false);

        try {
            doc.insertString(0, s, justify);
        } catch (BadLocationException ex) {
            Logger.getLogger(Practice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:

        System.out.println("Aligning text to LEFT");

        String s = textArea.getText();
        textArea.setText("");

        StyledDocument doc = textArea.getStyledDocument();
        SimpleAttributeSet left = new SimpleAttributeSet();
        StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
        doc.setParagraphAttributes(0, doc.getLength(), left, false);

        try {
            doc.insertString(0, s, left);
        } catch (BadLocationException ex) {
            Logger.getLogger(Practice.class.getName()).log(Level.SEVERE, null, ex);
        }}

    private void rightAlignActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:

        System.out.println("Aligning text to RIGHT");

        String s = textArea.getText();
        textArea.setText("");

        StyledDocument doc = textArea.getStyledDocument();
        SimpleAttributeSet right = new SimpleAttributeSet();
        StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
        doc.setParagraphAttributes(0, doc.getLength(), right, false);

        try {
            doc.insertString(0, s, right);
        } catch (BadLocationException ex) {
            Logger.getLogger(Practice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class MyHighlighterPainter extends DefaultHighlighter.DefaultHighlightPainter {
    public MyHighlighterPainter(Color c) {
        super(c);
    }
}