import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.rtf.RTFEditorKit;


public class MF extends JFrame {

    private JMenu Edit;
    private JMenu aboutHelp;
    private JMenuBar jMenuBar1;
    private JMenuItem jMenuItem1;
    private JMenuItem jMenuItem3;
    private JMenuItem jMenuItem6;
    private JMenuItem jMenuItem9;
    private JMenuItem FindandReplaceReview;
    private JMenuItem copyEdit;
    private JMenuItem cutEdit;

    private JLabel characterCount;
    private JLabel characterCountLabel;
    private JComboBox<String> fontSizeBox;
    private JLabel informationDisplay;
    private JComboBox<String> jComboBox1;
    private JLabel jLabel1;

    private JPanel jPanel1;
    private JScrollPane jScrollPane1;

    private JMenu newFile;
    private JMenuItem openFile;
    private JMenuItem saveFile;
    public JTextPane textArea;
    private JButton toLower;
    private JButton toUpper;
    private JLabel wordCount;
    private JLabel wordCountLabel;
    private JMenu wordCountReview;

    private static int SAVE;

    String[] fonts = {"Arial", "Calibri", "Cambria", "Courier New", "Comic Sans MS", "Dialog", "Georgia", "Helevetica", "Lucida Sans", "Monospaced","SignPainter", "Tahoma", "Times New Roman", "Verdana"};
    String[] fontSizes = {"5","10","15","20","25","30","35","40","45","50","55","60","65","70","75","80","85","90"};
    Font newFont = new Font("SignPainter",Font.PLAIN,20);

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

    File openedFile;
    RTFEditorKit editorKit;
    JFileChooser fileChooser = new JFileChooser();

    Highlighter.HighlightPainter myHighlightPainter = new MyHighlighterPainter(Color.yellow);
    Highlighter.HighlightPainter removeTheHighlight = new MyHighlighterPainter(Color.white);


    int removeHighlights = 0;


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
                h.addHighlight(findNextPos, findNextPos+s.length(), colourChoice);
                findNextPos += s.length();
            }

        }catch(BadLocationException e){}
    }

    public void removeHighlighter(JTextComponent textField, int remove){

        //If remove is 1, all the highlights are removed, but if remove is 0 then the highlights are removed one by one

        Highlighter highlighter = textArea.getHighlighter();
        Highlighter.Highlight[] highlighters = highlighter.getHighlights();

        if(remove==1){
            for (Highlighter.Highlight highlight : highlighters)
                if (highlight.getPainter() instanceof MyHighlighterPainter)
                    highlighter.removeHighlight(highlight);
        }
    }

    public MF() {
        initComponents();
        textArea.setFont(newFont);

        jComboBox1.removeAllItems();

        for(String s :fonts) jComboBox1.addItem(s);

        for(String s:fontSizes) fontSizeBox.addItem(s);

        this.setTitle(fileName + ".rtf");

    }

    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        textArea = new JTextPane();
        wordCountLabel = new JLabel();
        wordCount = new JLabel();
        characterCountLabel = new JLabel();
        characterCount = new JLabel();
        jPanel1 = new JPanel();
        jComboBox1 = new JComboBox<>();
        fontSizeBox = new JComboBox<>();
        toUpper = new JButton();
        toLower = new JButton();
        jLabel1 = new JLabel();
        informationDisplay = new JLabel();
        jMenuBar1 = new JMenuBar();
        newFile = new JMenu();
        jMenuItem1 = new JMenuItem();
        openFile = new JMenuItem();
        saveFile = new JMenuItem();
        jMenuItem3 = new JMenuItem();
        Edit = new JMenu();
        cutEdit = new JMenuItem();
        copyEdit = new JMenuItem();
        jMenuItem6 = new JMenuItem();
        wordCountReview = new JMenu();
        jMenuItem9 = new JMenuItem();
        FindandReplaceReview = new JMenuItem();
        aboutHelp = new JMenu();

        jScrollPane1.setViewportView(textArea);

        wordCountLabel.setText("Word Count :");

        wordCount.setText("0");

        characterCountLabel.setText("Character Count : ");

        characterCount.setText("0");

        jComboBox1.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(evt -> jComboBox1ActionPerformed());

        fontSizeBox.addActionListener(evt -> fontSizeBoxActionPerformed());

        jMenuItem6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.META_MASK));
        jMenuItem6.setText("Paste");
        jMenuItem6.addActionListener(evt -> jMenuItem6ActionPerformed());
        Edit.add(jMenuItem6);
        jMenuBar1.add(Edit);

        FindandReplaceReview.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.SHIFT_MASK | InputEvent.META_MASK));
        FindandReplaceReview.setText("Find and Replace");
        FindandReplaceReview.addActionListener(evt -> FindandReplaceReviewActionPerformed());
        wordCountReview.add(FindandReplaceReview);

        jMenuBar1.add(wordCountReview);

        setJMenuBar(jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(wordCountLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(wordCount)
                                                .addGap(38, 38, 38)
                                                .addComponent(characterCountLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(characterCount)
                                                .addGap(53, 53, 53)
                                                .addComponent(informationDisplay)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel1)
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 880, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(19, 19, 19)
                                                                .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(fontSizeBox, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(32, 32, 32)
                                                                .addComponent(toUpper)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(toLower)))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(fontSizeBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(toUpper, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(toLower, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 754, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(wordCountLabel)
                                                .addComponent(wordCount)
                                                .addComponent(characterCountLabel)
                                                .addComponent(characterCount)
                                                .addComponent(jLabel1))
                                        .addComponent(informationDisplay))
                                .addContainerGap(9, Short.MAX_VALUE))
        );

        pack();
    }


    //FIND AND REPLACE
    private void FindandReplaceReviewActionPerformed() {
        Find newFind = new Find();
        newFind.setVisible(true);
    }

    private void jComboBox1ActionPerformed() {
        //fontsSelectionsList
        String selectedFont = (String)jComboBox1.getSelectedItem();
        Font f = new Font(selectedFont,Font.PLAIN,previousSize);

        if(textArea.getSelectedText()==null) textArea.setFont(f);
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

    private void fontSizeBoxActionPerformed() {
        //fontsSelectionsList
        System.out.println("Setting font to "+jComboBox1.getSelectedItem());

        String s = fontSizeBox.getSelectedItem().toString();
        int selectedSize = Integer.valueOf(s);

        StyledDocument doc = (StyledDocument) textArea.getDocument();
        int selectionEnd = textArea.getSelectionEnd();
        int selectionStart = textArea.getSelectionStart();


        Element element = doc.getCharacterElement(selectionStart);
        AttributeSet as =  element.getAttributes();

        if(selectionEnd == selectionStart) return;
        MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
        StyleConstants.setFontSize(asNew, selectedSize);
        doc.setCharacterAttributes(selectionStart, textArea.getSelectedText()
                .length(), asNew, true);

        previousSize = selectedSize;

    }

    //PASTE
    private void jMenuItem6ActionPerformed() {
        try {
            document.insertString(textArea.getCaretPosition(), " " + copiedText + " ", attribute);
        } catch (BadLocationException ex) {
            Logger.getLogger(MF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MF.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> new MF().setVisible(true));
    }

    //FindAndReplace class

    class Find extends JFrame {
        //Creates new form Find

        public Find() {
            initComponents();
            foundInPlaces.setText("");
        }

        @SuppressWarnings("unchecked")
        private void initComponents() {

            findText = new JTextField();
            jButton1 = new JButton();
            replaceWithText = new JTextField();
            jLabel1 = new JLabel();
            jLabel2 = new JLabel();
            jButton2 = new JButton();
            jButton3 = new JButton();
            jButton4 = new JButton();
            foundInPlaces = new JLabel();

            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            findText.addActionListener(evt -> findTextActionPerformed());

            jButton1.setText("Find All");
            jButton1.addActionListener(evt -> jButton1ActionPerformed());

            jLabel1.setText("Find");

            jLabel2.setText("Replace with");

            jButton3.setText("Find Next");
            jButton3.addActionListener(e -> jButton3ActionPerformed());

            jButton4.setText("Replace");
            jButton4.addActionListener(evt -> jButton4ActionPerformed());
        }

        //ReplaceOneByOne
        private void jButton4ActionPerformed() {
            StyledDocument doc = (StyledDocument) textArea.getDocument();

            int pos = findNextPos - findText.getText().length();

            try {
                Element element = doc.getCharacterElement(pos);
                AttributeSet as =  element.getAttributes();
                doc.remove(pos, findText.getText().length());
                doc.insertString(pos, replaceWithText.getText(), as);

            } catch (BadLocationException ex) {
                Logger.getLogger(MF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //findNext
        private void jButton3ActionPerformed() {

            if(findNextPos==0){
                findNextHighlight(textArea, findText.getText(),myHighlightPainter);
            }
            else{
                try{

                    Highlighter h = textArea.getHighlighter();
                    Document doc = textArea.getDocument();
                    String text = doc.getText(0, doc.getLength());


                    removeHighlighter(textArea,1);
                    findNextHighlight(textArea, findText.getText(),myHighlightPainter);

                    oldFindNextPos = findNextPos;

                }catch(BadLocationException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        //FindAll
        private void jButton1ActionPerformed() {

            String s = textArea.getText();
            if(count>0){
                count=0;
                removeHighlighter(textArea,1);
            }

            if(s.isEmpty())
                JOptionPane.showMessageDialog(null, "Please enter the word to be searched");
            else{
                String[] array = s.split(" ");
                for(String arrayOne:array){
                    if(arrayOne.contains("\n")){
                        String[] newLineArray = arrayOne.split("\n");
                        for(String newLineSearch: newLineArray)if(newLineSearch.contains(findText.getText())) count++;

                    }else
                    if(arrayOne.contains(findText.getText())) count++;
                }
            }

            foundInPlaces.setText("Found in " + count + " places");

            highlight(textArea,findText.getText(),myHighlightPainter);

        }


        private void findTextActionPerformed() {
        }

        public void main(String[] args) {
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(Find.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(Find.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(Find.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(Find.class.getName()).log(Level.SEVERE, null, ex);
            }
            EventQueue.invokeLater(() -> new Find().setVisible(true));
        }

        public JTextField findText;
        private JLabel foundInPlaces;
        private JButton jButton1;
        private JButton jButton2;
        private JButton jButton3;
        private JButton jButton4;
        private JLabel jLabel1;
        private JLabel jLabel2;
        private JTextField replaceWithText;
    }
}

class MyHighlighterPainter extends DefaultHighlightPainter {
    public MyHighlighterPainter(Color c) {
        super(c);
    }
}