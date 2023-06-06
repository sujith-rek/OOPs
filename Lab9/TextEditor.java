import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.*;
import javax.swing.text.rtf.RTFEditorKit;

/**
 * Sujith
 * 2110110400
 * **/
class App extends JFrame{
    //Menu items
    private JMenuBar Menu;
    private JMenu File;
    private JMenuItem Open;
    private JMenuItem New;
    private JMenuItem Save;
    private JMenuItem SaveAs;
    private JMenuItem Exit;

    private JMenu Edit;
    private JMenuItem Cut;
    private JMenuItem Copy;
    private JMenuItem Paste;

    private JMenu Format;
    private JMenuItem formatText;
    private JMenu Review;
    private JMenuItem Find;

    private JMenu About;
    private JMenuItem about;

    //Text editing items
    JPanel utilities;
    JPanel fontPanel;
    JPanel alignPanel;
    JButton bold;
    JButton italics;
    JButton underline;
    JButton strikethrough;
    JButton leftalign;
    JButton rightalign;
    JButton centeralign;
    JButton toUpper;
    JButton toLower;
    JButton justify;

    //Content
    JTextPane text;

    //Font panel
    JPanel fontTypePanel;
    JComboBox<String> font;
    JComboBox<String> fontSize;

    //Wordcount panel
    JPanel WordCount;
    JLabel letterCount ;
    JLabel letters;
    JLabel wordCount;
    JLabel words;
    JLabel selectionStatus;

    boolean B,I,U,S,CA,JC,LA,RA,isSaved;
    File CurrentFile;

    JFileChooser fileOption = new JFileChooser();
    String fn="",dir="",NameOfFile="New Text Document",Copy_Cut_String;

    String[] fonts = {"Arial", "Calibri", "Cambria", "Courier New", "Comic Sans MS", "Dialog", "Georgia",
            "Helevetica", "Lucida Sans", "Monospaced","SignPainter", "Tahoma", "Times New Roman", "Verdana"};
    String[] fontSizes = {"5","10","15","20","25","30","35","40","45","50","55","60","65","70","75","80","85","90"};

    String fontName="Papyrus";
    int fontType=Font.PLAIN,fontWidth=25;
    Font textfont = new Font(fontName,fontType,fontWidth);

    App(){
        setTitle("Text Editor");
        setSize(900,550);
        setLocationRelativeTo(null);
        Menu = new JMenuBar();
        File = new JMenu("   File   ");
        Open = new JMenuItem("     Open       ");
        New = new JMenuItem("     New       ");
        Save = new JMenuItem("     Save       ");
        SaveAs = new JMenuItem("     SaveAs       ");
        Exit = new JMenuItem("     Exit       ");
        File.add(Open);
        File.add(New);
        File.add(Save);
        File.add(SaveAs);
        File.add(Exit);

        Edit = new JMenu("   Edit   ");
        Cut = new JMenuItem("     Cut       ");
        Copy = new JMenuItem("     Copy       ");
        Paste = new JMenuItem("     Paste       ");
        Edit.add(Cut);
        Edit.add(Copy);
        Edit.add(Paste);

        Review = new JMenu("  Review  ");
        Find = new JMenuItem("     Find       ");
        Review.add(Find);

        About = new JMenu("  About   ");
        about = new JMenuItem("     About this       ");
        About.add(about);
        Menu.add(File);
        Menu.add(Edit);
        Menu.add(Review);
        Menu.add(About);
        add(Menu, BorderLayout.NORTH);

        utilities = new JPanel(new GridLayout(4,1));
        fontPanel = new JPanel(new GridLayout(2,2));
        alignPanel = new JPanel(new GridLayout(2,2));
        bold = new JButton("B");
        italics = new JButton("I");
        underline = new JButton("U");
        strikethrough = new JButton("S");
        rightalign = new JButton("RA");
        leftalign = new JButton("LA");
        centeralign = new JButton("CA");
        justify = new JButton("JC");
        toLower = new JButton("To Lower");
        toUpper = new JButton("To Upper");
        fontPanel.add(bold);
        fontPanel.add(italics);
        fontPanel.add(underline);
        fontPanel.add(strikethrough);
        alignPanel.add(leftalign);
        alignPanel.add(rightalign);
        alignPanel.add(centeralign);
        alignPanel.add(justify);
        utilities.add(fontPanel);
        utilities.add(alignPanel);
        utilities.add(toLower);
        utilities.add(toUpper);
        add(utilities,BorderLayout.WEST);

        WordCount = new JPanel();
        WordCount.setBackground(new Color(0xD5D8DE));
        letterCount = new JLabel(" Character Count : ");
        letters = new JLabel(" ");
        wordCount = new JLabel(" Word Count : ");
        words = new JLabel(" ");
        selectionStatus = new JLabel(" COUNT OF WHOLE TEXT ");
        WordCount.add(selectionStatus);
        WordCount.add(letterCount);
        WordCount.add(letters);
        WordCount.add(wordCount);
        WordCount.add(words);
        add(WordCount,BorderLayout.SOUTH);

        fontTypePanel = new JPanel();
        font = new JComboBox<>(fonts);
        fontSize = new JComboBox<>(fontSizes);
        fontTypePanel.add(font);
        fontTypePanel.add(fontSize);
        add(fontTypePanel,BorderLayout.EAST);
        text = new JTextPane();
        text.setFont(textfont);
        add(text);
    }
    void start(){
        setVisible(true);

        about.addActionListener(e->{
            JOptionPane.showMessageDialog(null, " Soma Sujith \n 2110110400");
        });

        bold.addActionListener(e->{
            B = !B;
            if(B)fontType = Font.BOLD;
            else fontType = Font.PLAIN;
            text.setFont(new Font(fontName,fontType,fontWidth));
        });
        italics.addActionListener(e -> {
            I = !I;
            if(I) fontType = Font.ITALIC;
            else fontType = Font.PLAIN;
            text.setFont(new Font(fontName,fontType,fontWidth));
        });
        underline.addActionListener(e -> {
            StyledDocument doc = (StyledDocument) text.getDocument();
            int selectionEnd = text.getSelectionEnd();
            int selectionStart = text.getSelectionStart();

            Element element = doc.getCharacterElement(selectionStart);
            AttributeSet as =  element.getAttributes();

            if(selectionEnd == selectionStart) return;
            MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
            StyleConstants.setUnderline(asNew, !StyleConstants.isUnderline(as));
            doc.setCharacterAttributes(selectionStart, text.getSelectedText()
                    .length(), asNew, true);
        });
        strikethrough.addActionListener(e -> {
            StyledDocument doc = (StyledDocument) text.getDocument();
            int selectionEnd = text.getSelectionEnd();
            int selectionStart = text.getSelectionStart();

            Element element = doc.getCharacterElement(selectionStart);
            AttributeSet as =  element.getAttributes();

            if(selectionEnd == selectionStart) return;

            MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
            StyleConstants.setStrikeThrough(asNew, !StyleConstants.isStrikeThrough(as));
            doc.setCharacterAttributes(selectionStart, text.getSelectedText()
                    .length(), asNew, true);
        });
        rightalign.addActionListener(e->{
            StyledDocument doc = text.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_RIGHT);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
        });
        centeralign.addActionListener(e -> {
            StyledDocument doc = text.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
        });
        leftalign.addActionListener(e -> {
            StyledDocument doc = text.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_LEFT);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
        });
        justify.addActionListener(e -> {
            StyledDocument doc = text.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_JUSTIFIED);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
        });
        toUpper.addActionListener(e->{
            String s = text.getSelectedText();
            int start = text.getSelectionStart();
            int end = text.getSelectionEnd();
            StringBuffer dup = new StringBuffer(text.getText());
            dup = dup.replace(start,end,s.toUpperCase());
            text.setText(String.valueOf(dup));
        });
        toLower.addActionListener(e->{
            String s = text.getSelectedText();
            int start = text.getSelectionStart();
            int end = text.getSelectionEnd();
            StringBuffer dup = new StringBuffer(text.getText()) ;
            dup = dup.replace(start,end,s.toLowerCase());
            text.setText(String.valueOf(dup));
        });

        text.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                count();
            }
            public void keyPressed(KeyEvent e) {
                count();
            }
            public void keyReleased(KeyEvent e) {
                count();
            }
        });
        text.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                count();
            }
        });
        text.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                count();
            }
        });

        New.addActionListener(e -> {
            if(!isSaved) SaveFile();
            else{
                this.setTitle("New Text Document");
                text.setText("");
                text.setFont(textfont);
                isSaved = false;
            }
        });
        Open.addActionListener(e -> {
            text.setText("");
            App.this.fileOption.showOpenDialog(null);
            CurrentFile = App.this.fileOption.getSelectedFile();
            RTFEditorKit editorKit = new RTFEditorKit();
            FileInputStream inputStream;
            try {
                inputStream = new FileInputStream(CurrentFile);
                editorKit.read(inputStream,text.getStyledDocument(), 0);
                inputStream.close();
            } catch (BadLocationException e1) {
                e1.getCause();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        });
        Save.addActionListener(e -> {
                SaveFile();
        });
        SaveAs.addActionListener(e -> {
                SaveAsFile();
        });
        Cut.addActionListener(e -> {
            Copy_Cut_String = text.getSelectedText();
            int start = text.getSelectionStart();
            int end = text.getSelectionEnd();
            StringBuffer sb = new StringBuffer(text.getText());
            sb.replace(start,end,"");
            text.setText(String.valueOf(sb));
        });
        Copy.addActionListener(e -> {
            Copy_Cut_String = text.getSelectedText();
        });
        Paste.addActionListener(e -> {
            int pos = text.getCaretPosition();
            StringBuffer s = new StringBuffer(text.getText());
            s.insert(pos, Copy_Cut_String);
            text.setText(String.valueOf(s));
        });
        Find.addActionListener(e -> {
            FindWord f = new FindWord();
            f.start(text);
        });

        Exit.addActionListener(e-> System.exit(0));

        font.addActionListener(e -> {
            String selectedFont = (String)font.getSelectedItem();
            Font f = new Font(selectedFont,Font.PLAIN,fontWidth);
            if(text.getSelectedText()==null) text.setFont(f);
            else{
                StyledDocument doc = (StyledDocument) text.getDocument();
                int selectionEnd = text.getSelectionEnd();
                int selectionStart = text.getSelectionStart();
                Element elements = doc.getCharacterElement(selectionStart);
                AttributeSet as =  elements.getAttributes();
                MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
                StyleConstants.setFontFamily(asNew, selectedFont);
                doc.setCharacterAttributes(selectionStart, text.getSelectedText()
                        .length(), asNew, true);
            }
        });
        fontSize.addActionListener(e->{
            String s = fontSize.getSelectedItem().toString();
            int selectedSize = Integer.parseInt(s);

            StyledDocument doc = (StyledDocument) text.getDocument();
            int selectionEnd = text.getSelectionEnd();
            int selectionStart = text.getSelectionStart();

            Element element = doc.getCharacterElement(selectionStart);
            AttributeSet as =  element.getAttributes();

            if(selectionEnd == selectionStart) return;

            MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
            StyleConstants.setFontSize(asNew, selectedSize);
            doc.setCharacterAttributes(selectionStart, text.getSelectedText()
                    .length(), asNew, true);

            fontWidth = selectedSize;
        });
    }

    /** COUNTS THE NUMBER OF WORDS AND LETTERS
     *  WORDS ARE ENCLOSED BY SPACES SO WE WILL
     *  SPLIT THE TEXT WITH REGEX SPACE*/
    void count(){
        String s;
        int counter=0,WORDS= 0,LETTERS = 0,flag=0;

        if(text.getSelectedText()==null) s = text.getText();
        else{
            s = text.getSelectedText();
            counter=1;
        }
        if(s.isEmpty()){
            WORDS=0;
            LETTERS=0;
        }else{
            String[] array = s.split(" ");
            for(String d : array){
                String[] newLine = d.split("\n");
                if(newLine.length != 1){
                    flag=1;
                    for(String line:newLine){
                        if(!line.equals("")) WORDS++;
                        LETTERS += line.length();
                    }
                }
                if(flag==0){
                    WORDS++;
                    LETTERS += d.length();
                }
                flag=0;
            }
        }

        letters.setText(String.valueOf(LETTERS));
        words.setText(String.valueOf(WORDS));
        if(counter==1) selectionStatus.setText("COUNT OF SELECTED TEXT");
        else selectionStatus.setText("COUNT OF WHOLE TEXT");
    }

    void SaveFile(){
        int flag = fileOption.showSaveDialog(null);
        if (flag == JFileChooser.APPROVE_OPTION) {
            File saveFiles = fileOption.getSelectedFile();
            StyledDocument documents = text.getStyledDocument();
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
            NameOfFile = fileOption.getName(saveFiles);
            this.setTitle(NameOfFile+ ".rtf");
            isSaved=true;
        }
    }
    void SaveAsFile() {
        SaveFile();
    }
}

class FindWord extends JFrame{
    JPanel totalPane;
    JLabel statusPanel;
    JPanel buttonPane;
    JLabel find;
    JLabel replace;
    JTextField findText;
    JTextField replaceText;

    int curPos=0,nextPos=0,prevPos=0,count=0;
    JButton findAll,findNext,Replace,replaceAll;
    Highlighter.HighlightPainter myHighlightPainter = new MyHighlighterPainter(Color.orange);
    Highlighter.HighlightPainter removeTheHighlight = new MyHighlighterPainter(Color.white);
    FindWord(){
        setTitle("Find and Replace");
        setLocationRelativeTo(null);
        setSize(500,300);
        totalPane = new JPanel(new GridLayout(7,1));
        find = new JLabel("  Find Text : ");
        totalPane.add(find);
        findText = new JTextField();
        totalPane.add(findText);
        replace = new JLabel("  Replace With : ");
        totalPane.add(replace);
        replaceText = new JTextField();
        totalPane.add(replaceText);

        statusPanel = new JLabel("");
        totalPane.add(statusPanel);

        buttonPane = new JPanel(new GridLayout(1,4,20,10));
        findAll = new JButton(" Find All ");
        findNext = new JButton(" Find Next ");
        Replace = new JButton(" Replace ");
        replaceAll = new JButton(" Replace All ");
        buttonPane.add(findAll);
        buttonPane.add(findNext);
        buttonPane.add(Replace);
        buttonPane.add(replaceAll);
        totalPane.add(buttonPane);
        add(totalPane,BorderLayout.CENTER);
    }

    void start(JTextPane textPane){
        setVisible(true);
        findAll.addActionListener(e -> {
            String s = textPane.getText();
            if(count>0){
                count=0;
                removeHighlighter(textPane,1);
            }

            if(s.isEmpty())
                JOptionPane.showMessageDialog(null, "enter the word to search");
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
            statusPanel.setText("Found in " + count + " places");
            highlight(textPane,findText.getText(),myHighlightPainter);
        });
        findNext.addActionListener(e->{
            if(nextPos==0)
                findNextHighlight(textPane, findText.getText(),myHighlightPainter);
            else{
                try{
                    Highlighter h = textPane.getHighlighter();
                    Document doc = textPane.getDocument();
                    String text = doc.getText(0, doc.getLength());
                    removeHighlighter(textPane,1);
                    findNextHighlight(textPane, findText.getText(),myHighlightPainter);

                    prevPos = nextPos;

                }catch(BadLocationException e1){
                }
            }
        });
        Replace.addActionListener(e->{
            StyledDocument doc = (StyledDocument) textPane.getDocument();
            int pos = nextPos - findText.getText().length();
            try {
                Element element = doc.getCharacterElement(pos);
                AttributeSet as =  element.getAttributes();
                doc.remove(pos, findText.getText().length());
                doc.insertString(pos, replaceText.getText(), as);

            } catch (BadLocationException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        replaceAll.addActionListener(e->{
            String fin = findText.getText();
            String rep = replaceText.getText();
            String t = textPane.getText();
            t=t.replaceAll(fin,rep);
            textPane.setText(t);
        });
    }
    void highlight(JTextComponent textComponent, String s, Highlighter.HighlightPainter colourChoice){
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

    void findNextHighlight(JTextComponent textComponent, String s, Highlighter.HighlightPainter colourChoice){
        try{
            Highlighter h = textComponent.getHighlighter();
            Document doc = textComponent.getDocument();
            String text = doc.getText(0, doc.getLength());

            if((nextPos = text.toUpperCase().indexOf(s.toUpperCase(),nextPos))>=0){
                h.addHighlight(nextPos, nextPos+s.length(), colourChoice);
                nextPos += s.length();
            }

        }catch(BadLocationException e){}
    }

    public void removeHighlighter(JTextComponent textField, int remove){

        Highlighter highlighter = textField.getHighlighter();
        Highlighter.Highlight[] highlighters = highlighter.getHighlights();

        if(remove==1){
            for (Highlighter.Highlight highlight : highlighters)
                if (highlight.getPainter() instanceof MyHighlighterPainter)
                    highlighter.removeHighlight(highlight);
        }
    }
}

class HighLightText extends DefaultHighlighter.DefaultHighlightPainter {
    public HighLightText(Color c) {
        super(c);
    }
}

public class TextEditor {
    public static void main(String[] args) {
        App texteditor = new App();
        texteditor.start();
    }
}