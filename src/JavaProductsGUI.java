import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.*;

public class JavaProductsGUI {
    private JPanel Main;
    private JTextField textname;
    private JTextField textprice;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JTextField textpid;
    private JTextField textqty;
    private JButton searchButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("JavaProductsGUI");
        frame.setContentPane(new JavaProductsGUI().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public JavaProductsGUI() {
        Connect();
        saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                String name, price, qty;

                name = textname.getText();
                qty = textqty.getText();
                price = textprice.getText();
                try{
                    pst = con.prepareStatement("insert into Products(pname,price,qty) value(?,?,?)");
                    pst.setString(1,name);
                    pst.setString(2,price);
                    pst.setString(3,qty);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record added");
                    textname.setText("");
                    textprice.setText("");
                    textqty.setText("");
                    textname.requestFocus();

                }
                catch(SQLException e1){
                    e1.printStackTrace();
                }
        }
    });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String del_id = textpid.getText();


                try{
                    pst = con.prepareStatement("Delete from Products where pid = ?");
                    pst.setString(1,del_id);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record delete");
                    textname.setText("");
                    textpid.setText("");
                    textprice.setText("");
                    textqty.setText("");
                    textname.requestFocus();

                }
                catch(SQLException e2){
                    e2.printStackTrace();
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pid,pname,price,qty;

                pname = textname.getText();
                price = textprice.getText();
                qty = textqty.getText();
                pid = textpid.getText();

                try{
                    pst = con.prepareStatement("Update Products set pname = ?, price = ?, qty = ? where pid = ?");
                    pst.setString(1, pname);
                    pst.setString(2, price);
                    pst.setString(3, qty);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"somethng");
                    textname.setText(pname);
                    textprice.setText(price);
                    textqty.setText(qty);
                    textpid.setText(pid);
                    textname.requestFocus();


                }
                catch(SQLException e3){
                    e3.printStackTrace();
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                   String pid = textpid.getText();

                   pst = con.prepareStatement("Select pname,price,qty from Products where pid = ?");
                   pst.setString(1,pid);
                   ResultSet r = pst.executeQuery();

                   if(r.next()==true){
                       String pname = r.getString(1);
                       String price = r.getString(2);
                       String qty = r.getString(3);

                       textname.setText(pname);
                       textprice.setText(price);
                       textqty.setText(qty);

                   }
                   else{
                       textname.setText("");
                       textprice.setText("");
                       textqty.setText("");
                       JOptionPane.showMessageDialog(null,"Error");
                   }
                }
                catch(SQLException e4){
                    e4.printStackTrace();
                }
            }
        });
    }
    Connection con;
    PreparedStatement pst;

public void Connect(){
    try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/JavaProducts", "root", "");
        System.out.println("Success");
    }
    catch(ClassNotFoundException e){
        e.printStackTrace();
    }
    catch(SQLException e){
        e.printStackTrace();
    }
}
}
