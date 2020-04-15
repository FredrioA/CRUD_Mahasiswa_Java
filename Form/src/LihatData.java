import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;


public class LihatData extends JFrame{
    String[][] data = new String[480][3];
    String[] kolom = {"NIM", "NAMA", "ALAMAT"};
    JTable tabel;
    JButton btnBack;
    JScrollPane scrollPane;
    Statement statement;
    ResultSet resultSet;
    
    public void LihatData(){
        setTitle("Data Mahasiswa");
        
        btnBack = new JButton("Kembali");
        tabel = new JTable(data, kolom);
        scrollPane = new JScrollPane(tabel);
        
        setLayout(new FlowLayout());
        add(scrollPane);
        add(btnBack);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible (false);
                new formmhs();
            }
        });
        
        KoneksiDB koneksi = new KoneksiDB();
        try{
            statement = koneksi.getKoneksi().createStatement();
            String sql = "select *from data_mhs";
            resultSet = statement.executeQuery(sql);
            int p = 0;
            while(resultSet.next()){
                data[p][0] = resultSet.getString("nim");
                data[p][1] = resultSet.getString("nama");
                data[p][2] = resultSet.getString("alamat");
                p++;
            }
            statement.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Data Gagal Ditampilkan!", "Hasil", JOptionPane.ERROR_MESSAGE);
        }catch(ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Driver Tidak Ditemukan!", "Hasil", JOptionPane.ERROR_MESSAGE);
        }
    }
}
