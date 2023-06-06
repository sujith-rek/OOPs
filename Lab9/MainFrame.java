import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
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


public class MainFrame extends JFrame {

    private JMenu Edit;
    private JMenu Format;
    private JMenu aboutHelp;
    private JMenuBar jMenuBar1;
    private JMenuItem jMenuItem1;
    private JMenuItem jMenuItem12;
    private JMenuItem jMenuItem2;
    private JMenuItem jMenuItem3;
    private JMenuItem jMenuItem4;
    private JMenuItem jMenuItem5;
    private JMenuItem jMenuItem6;
    private JMenuItem jMenuItem7;
    private JMenuItem jMenuItem8;
    private JMenuItem jMenuItem9;
    private JMenuItem FindandReplaceReview;
    private JMenuItem copyEdit;
    private JMenuItem cutEdit;

    private JMenuItem boldFormat;
    private JMenuItem centreAlign;
    private JMenuItem findReview;

    private JLabel characterCount;
    private JLabel characterCountLabel;
    private JComboBox<String> fontSizeBox;
    private JMenu fontsFormat;
    private JLabel informationDisplay;
    private JComboBox<String> jComboBox1;
    private JLabel jLabel1;

    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JMenuItem justifyAlign;
    private JMenu leftAlign;
    private JMenu newFile;
    private JMenuItem openFile;
    private JMenuItem rightAlign;
    private JMenuItem saveFile;
    private JMenu sizeFormat;
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


        }catch(BadLocationException e){}
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

    public MainFrame() {
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
        jMenuItem4 = new JMenuItem();
        Edit = new JMenu();
        cutEdit = new JMenuItem();
        copyEdit = new JMenuItem();
        jMenuItem6 = new JMenuItem();
        leftAlign = new JMenu();
        jMenuItem2 = new JMenuItem();
        centreAlign = new JMenuItem();
        rightAlign = new JMenuItem();
        justifyAlign = new JMenuItem();
        Format = new JMenu();
        boldFormat = new JMenuItem();
        jMenuItem7 = new JMenuItem();
        jMenuItem8 = new JMenuItem();
        jMenuItem5 = new JMenuItem();
        sizeFormat = new JMenu();
        fontsFormat = new JMenu();
        wordCountReview = new JMenu();
        jMenuItem9 = new JMenuItem();
        findReview = new JMenuItem();
        FindandReplaceReview = new JMenuItem();
        aboutHelp = new JMenu();
        jMenuItem12 = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        textArea.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                textAreaMouseDragged();
            }
        });
        textArea.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                textAreaMouseClicked(evt);
            }
        });
        textArea.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                textAreaKeyTyped(evt);
            }
            public void keyReleased(KeyEvent evt) {
                textAreaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(textArea);

        wordCountLabel.setText("Word Count :");

        wordCount.setText("0");

        characterCountLabel.setText("Character Count : ");

        characterCount.setText("0");

        jComboBox1.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(evt -> jComboBox1ActionPerformed());

        fontSizeBox.addActionListener(evt -> fontSizeBoxActionPerformed());

        toUpper.setText("toUpper");
        toUpper.addActionListener(evt -> toUpperActionPerformed());

        toLower.setText("toLower");
        toLower.addActionListener(evt -> toLowerActionPerformed());

        newFile.setText("File");

        jMenuItem1.setText("New");
        jMenuItem1.addActionListener(evt -> NewActionPerformed());
        newFile.add(jMenuItem1);

        openFile.setText("Open");
        openFile.addActionListener(evt -> openFileActionPerformed());
        newFile.add(openFile);

        saveFile.setText("Save");
        saveFile.addActionListener(evt -> saveFileActionPerformed());
        newFile.add(saveFile);

        jMenuItem3.setText("Save As");
        jMenuItem3.addActionListener(evt -> SaveAsActionPerformed());
        newFile.add(jMenuItem3);

        jMenuItem4.setText("Exit");
        jMenuItem4.addActionListener(evt -> EXIT_Screen());
        newFile.add(jMenuItem4);

        jMenuBar1.add(newFile);

        Edit.setText("Edit");

        cutEdit.setText("Cut");
        cutEdit.addActionListener(evt -> cutEditActionPerformed());
        Edit.add(cutEdit);

        copyEdit.setText("Copy");
        copyEdit.addActionListener(evt -> copyEditActionPerformed(evt));
        Edit.add(copyEdit);

        jMenuItem6.setText("Paste");
        jMenuItem6.addActionListener(evt -> PasteActionPerformed());
        Edit.add(jMenuItem6);

        leftAlign.setText("Alignment");

        jMenuItem2.setText("Left");
        jMenuItem2.addActionListener(evt -> leftAlignActionPerformed());
        leftAlign.add(jMenuItem2);

        centreAlign.setText("Centre");
        centreAlign.addActionListener(evt -> centreAlignActionPerformed());
        leftAlign.add(centreAlign);

        rightAlign.setText("Right");
        rightAlign.addActionListener(evt -> rightAlignActionPerformed());
        leftAlign.add(rightAlign);

        justifyAlign.setText("Justify");
        justifyAlign.addActionListener(evt -> justifyAlignActionPerformed());
        leftAlign.add(justifyAlign);

        Edit.add(leftAlign);

        jMenuBar1.add(Edit);

        Format.setText("Format");
        Format.addActionListener(evt -> makeTextItalics());

        boldFormat.setText("Bold");
        boldFormat.addActionListener(evt -> makeTextBold());
        Format.add(boldFormat);

        jMenuItem7.setText("Italics");
        jMenuItem7.addActionListener(evt -> makeTextItalics());
        Format.add(jMenuItem7);

        jMenuItem8.setText("Underline");
        jMenuItem8.addActionListener(evt -> makeTextUnderlined());
        Format.add(jMenuItem8);

        jMenuItem5.setText("Strikethrough");
        jMenuItem5.addActionListener(evt -> MakeTextStrikeThrough());
        Format.add(jMenuItem5);

        sizeFormat.setText("Size");
        Format.add(sizeFormat);

        fontsFormat.setText("Fonts");
        Format.add(fontsFormat);

        jMenuBar1.add(Format);

        wordCountReview.setText("Review");

        jMenuItem9.setText("Word Count");
        jMenuItem9.addActionListener(evt -> jMenuItem9ActionPerformed());
        wordCountReview.add(jMenuItem9);

        findReview.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.META_MASK));
        findReview.setText("Find");
        findReview.addActionListener(evt -> findReviewActionPerformed());
        wordCountReview.add(findReview);

        FindandReplaceReview.setText("Find and Replace");
        FindandReplaceReview.addActionListener(evt -> FindandReplaceReviewActionPerformed());
        wordCountReview.add(FindandReplaceReview);

        jMenuBar1.add(wordCountReview);

        aboutHelp.setText("Help");

        jMenuItem12.setText("About");
        jMenuItem12.addActionListener(evt -> jMenuItem12ActionPerformed());
        aboutHelp.add(jMenuItem12);

        jMenuBar1.add(aboutHelp);

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

    private void EXIT_Screen() {
        //Exit_File
        System.exit(0);
    }

    private void jMenuItem12ActionPerformed() {
        //About_Help
        JOptionPane.showMessageDialog(null, " Gautam Nanda \n 2110110222");
    }

    //word_count
    private void jMenuItem9ActionPerformed() {
        //WORD COUNT
        wordCount();
    }

    private void makeTextBold() {
        StyledDocument doc = (StyledDocument) textArea.getDocument();
        int selectionEnd = textArea.getSelectionEnd();
        int selectionStart = textArea.getSelectionStart();

        Element element = doc.getCharacterElement(selectionStart);
        AttributeSet as =  element.getAttributes();

        if(selectionEnd == selectionStart) return;


        MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
        StyleConstants.setBold(asNew, !StyleConstants.isBold(as));
        doc.setCharacterAttributes(selectionStart, textArea.getSelectedText()
                .length(), asNew, true);

    }

    private void makeTextItalics() {

        StyledDocument doc = (StyledDocument) textArea.getDocument();
        int selectionEnd = textArea.getSelectionEnd();
        int selectionStart = textArea.getSelectionStart();

        Element element = doc.getCharacterElement(selectionStart);
        AttributeSet as = element.getAttributes();

        if (selectionEnd != selectionStart) {
            MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
            StyleConstants.setItalic(asNew, !StyleConstants.isItalic(as));
            doc.setCharacterAttributes(selectionStart, textArea.getSelectedText()
                    .length(), asNew, true);
        }
    }

//     MAKE TEXT STRIKETHROUGH  AND UNDERLINED
    private void makeTextUnderlined() {

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

    private void MakeTextStrikeThrough() {
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
//        //fontsSelectionsList
    private void fontSizeBoxActionPerformed() {

        String s = fontSizeBox.getSelectedItem().toString();
        int selectedSize = Integer.parseInt(s);

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

    private void textAreaKeyTyped(KeyEvent evt) {
    }

    private void rightAlignActionPerformed() {
        String s = textArea.getText();
        textArea.setText("");

        StyledDocument doc = textArea.getStyledDocument();
        SimpleAttributeSet right = new SimpleAttributeSet();
        StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
        doc.setParagraphAttributes(0, doc.getLength(), right, false);

        try {
            doc.insertString(0, s, right);
        } catch (BadLocationException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void justifyAlignActionPerformed() {
        String s = textArea.getText();
        textArea.setText("");

        StyledDocument doc = textArea.getStyledDocument();
        SimpleAttributeSet justify = new SimpleAttributeSet();
        StyleConstants.setAlignment(justify, StyleConstants.ALIGN_JUSTIFIED);
        doc.setParagraphAttributes(0, doc.getLength(), justify, false);

        try {
            doc.insertString(0, s, justify);
        } catch (BadLocationException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void leftAlignActionPerformed() {
        String s = textArea.getText();
        textArea.setText("");

        StyledDocument doc = textArea.getStyledDocument();
        SimpleAttributeSet left = new SimpleAttributeSet();
        StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
        doc.setParagraphAttributes(0, doc.getLength(), left, false);

        try {
            doc.insertString(0, s, left);
        } catch (BadLocationException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void centreAlignActionPerformed() {
        String s = textArea.getText();
        textArea.setText("");

        StyledDocument doc = textArea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        try {
            doc.insertString(0, s, center);
        } catch (BadLocationException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void toUpperActionPerformed() {
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
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void toLowerActionPerformed() {
        String s = textArea.getSelectedText().toUpperCase();

        StyledDocument doc = (StyledDocument) textArea.getStyledDocument();
        int selectionEnd = textArea.getSelectionEnd();
        int selectionStart = textArea.getSelectionStart();

        Element elements = doc.getCharacterElement(selectionStart);
        AttributeSet as =  elements.getAttributes();

        if(selectionEnd == selectionStart){
            return;
        }

        try {
            doc.remove(selectionStart, s.length());
            doc.insertString(selectionStart, s.toLowerCase(), as);
        } catch (BadLocationException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //NEW FILE
    private void NewActionPerformed() {
        if(currentFileSaved==0) this.saveFileActionPerformed();
        else{
            this.setTitle("New Text Document");
            textArea.setText(" ");
            textArea.setFont(newFont);
            currentFileSaved=0;
        }

    }

    //OPEN
    private void openFileActionPerformed() {

        JFileChooser fileChooser = new JFileChooser();
        textArea.setText("");
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

    //Save_File
    private void saveFileActionPerformed() {
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

    //SAVE_AS
    private void SaveAsActionPerformed() {
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

    //CUT
    private void cutEditActionPerformed() {
        document = (StyledDocument) textArea.getDocument();
        int selectionEnd = textArea.getSelectionEnd();
        int selectionStart = textArea.getSelectionStart();
        element = document.getCharacterElement(selectionStart);
        attribute = element.getAttributes();
        copiedText = textArea.getSelectedText();
        try {
            document.remove(selectionStart, textArea.getSelectedText().length());
        } catch (BadLocationException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //PASTE
    private void PasteActionPerformed() {
        try {
            document.insertString(textArea.getCaretPosition(), " " + copiedText + " ", attribute);
        } catch (BadLocationException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //COPY
    private void copyEditActionPerformed(ActionEvent evt) {
        document = (StyledDocument) textArea.getDocument();
        int selectionEnd = textArea.getSelectionEnd();
        int selectionStart = textArea.getSelectionStart();
        element = document.getCharacterElement(selectionStart);
        attribute = element.getAttributes();
        copiedText = textArea.getSelectedText();
    }

    //FIND
    private void findReviewActionPerformed() {}
    //FIND AND REPLACE
    private void FindandReplaceReviewActionPerformed() {
        Find newFind = new Find();
        newFind.setVisible(true);
    }

    private void textAreaMouseDragged() {
        wordCount();
    }

    public void wordCount(){

        String s;
        int counter=0;

        if(textArea.getSelectedText()==null){
            s = textArea.getText();
        }else{
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
                        if(!line.equals("")) words++;
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
        if(counter==1)
            informationDisplay.setText("WORD COUNT OF SELECTED TEXT ONLY");
        else
            informationDisplay.setText("WORD COUNT OF ENTIRE DOCUMENT");

    }


    private void textAreaMouseClicked(MouseEvent evt) {
        wordCount();
    }

    private void textAreaKeyReleased(KeyEvent evt) {
        wordCount();
    }

    public String getTextFromTextArea(){
        return textArea.getText();
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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }

    //FindAndReplace class

    class Find extends JFrame {
        public JTextField findText;
        private JLabel foundInPlaces;
        private JButton jButton1;
        private JButton jButton2;
        private JButton jButton3;
        private JButton jButton4;
        private JLabel jLabel1;
        private JLabel jLabel2;
        private JTextField replaceWithText;

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

            findText.addActionListener(evt -> findTextActionPerformed());

            jButton1.setText("Find All");
            jButton1.addActionListener(evt -> FindALLPerformed());

            jLabel1.setText("Find");

            jLabel2.setText("Replace with");

            jButton2.setText("Replace All");
            jButton2.addActionListener(evt -> ReplaceALLActionPerformed());

            jButton3.setText("Find Next");
            jButton3.addActionListener(e -> FindNEXTActionPerformed());

            jButton4.setText("Replace");
            jButton4.addActionListener(evt -> ReplaceOneByOneActionPerformed());

            GroupLayout layout = new GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGap(28, 28, 28)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel1)
                                            .addComponent(replaceWithText)
                                            .addComponent(findText)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                            .addComponent(jButton1)
                                                            .addComponent(foundInPlaces))
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jButton3)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jButton4)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jButton2)))
                                    .addContainerGap(30, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addComponent(jLabel1)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(findText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(26, 26, 26)
                                    .addComponent(jLabel2)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(replaceWithText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(jButton1)
                                            .addComponent(jButton3)
                                            .addComponent(jButton4)
                                            .addComponent(jButton2))
                                    .addGap(18, 18, 18)
                                    .addComponent(foundInPlaces)
                                    .addContainerGap(35, Short.MAX_VALUE))
            );
            pack();
        }

        //ReplaceAll
        private void ReplaceALLActionPerformed(){
            removeHighlighter(textArea,1);
            StyledDocument doc = (StyledDocument) textArea.getDocument();

            int pos=0;
            try {
                while((pos = textArea.getText().toUpperCase().indexOf(findText.getText().toUpperCase(), pos))>=0){
                    Element element = doc.getCharacterElement(pos);
                    AttributeSet as =  element.getAttributes();
                    doc.remove(pos, findText.getText().length());
                    doc.insertString(pos, replaceWithText.getText(), as);
                    pos+= replaceWithText.getText().length();
                }
            } catch (BadLocationException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //ReplaceOneByOne
        private void ReplaceOneByOneActionPerformed() {
            StyledDocument doc = (StyledDocument) textArea.getDocument();
            int pos = findNextPos - findText.getText().length();
            try {
                Element element = doc.getCharacterElement(pos);
                AttributeSet as =  element.getAttributes();
                doc.remove(pos, findText.getText().length());
                doc.insertString(pos, replaceWithText.getText(), as);

            } catch (BadLocationException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //findNext
        private void FindNEXTActionPerformed() {

            if(findNextPos==0)
                findNextHighlight(textArea, findText.getText(),myHighlightPainter);
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
        private void FindALLPerformed() {

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
    }
}

class MyHighlighterPainter extends DefaultHighlightPainter {
    public MyHighlighterPainter(Color c) {
        super(c);
    }
}