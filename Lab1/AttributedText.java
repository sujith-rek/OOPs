import javax.swing.*;

public class AttributedText{
        public static void main(String[] args) {
                JFrame f = new JFrame("JF");
                JMenu men = new JMenu("Men");
                JMenuItem mo = new JMenuItem("MO");
                men.add(mo);
                JMenuItem no = new JMenuItem("niO");
                men.add(no);
                JMenuItem bo = new JMenuItem("niO");
                men.add(bo);
                JMenuItem co = new JMenuItem("nijioO");
                men.add(co);

                JMenu nen = new JMenu("Men");
                JMenuItem jo = new JMenuItem("MO");
                nen.add(jo);
                JMenuItem ko = new JMenuItem("niO");
                nen.add(ko);
                JMenuItem lo = new JMenuItem("niO");
                nen.add(lo);
                JMenuItem oo = new JMenuItem("nijioO");
                nen.add(oo);
                men.add(nen);
                JMenuBar mb = new JMenuBar();
                mb.add(men);
                f.add(mb);
                f.setVisible(true);
                f.setSize(400,400);
                f.setLocationRelativeTo(null);

        }
}
