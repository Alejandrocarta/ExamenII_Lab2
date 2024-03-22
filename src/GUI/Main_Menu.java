package GUI;

import Logic.PSNUser;
import Logic.Trophy;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 *
 * @author aleja
 */
public class Main_Menu extends JFrame{
    public static TrophyPanel TP;
    public static UserInfo UsIn;
    public static MainPanel MP;
    
    public Main_Menu(PSNUser PSN){
        setLayout(null);
        setSize(650,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        MP = new MainPanel(PSN);
        add(MP);
        TP = new TrophyPanel(PSN);
        TP.Reveal(false);
        add(TP);
        UsIn = new UserInfo(PSN);
        UsIn.Reveal(false);
        add(UsIn);
    }
    
}

class MainPanel extends JPanel{
    private final PSNUser PSN;
    
    public MainPanel(PSNUser PSN){
       this.PSN = PSN;
       
       setLayout(null);
       setBackground(Color.darkGray);
       setBounds(0,0,350,305);
       setSize(650,500);
       
       setBTNs();
       setLabels();
    }
    
    private void setLabels(){
        JLabel PSIcon = new JLabel();
        
        PSIcon.setBounds(0, 0, 40, 40);
        
        JLabel PSHeader = new JLabel();
        
        PSHeader.setBounds(40, 0, 270, 40);
    }
    
    private void setBTNs(){
        JButton AddUser = new JButton();
        
        AddUser.addActionListener((ActionEvent Ev) ->{
            try {
                String NeoUser = JOptionPane.showInputDialog(this, "Ingrese el nombre de usuario: ");
                if (!NeoUser.isBlank()){
                    if (PSN.AddUser(NeoUser)){
                        JOptionPane.showMessageDialog(this, "¡Se ha agregado el usuario exitosamente!", "Agregar usuario", JOptionPane.INFORMATION_MESSAGE);
                    } else JOptionPane.showMessageDialog(this, "¡Ha ocurrido un problema intentando agregar el usuario!", "Agregar usuario", JOptionPane.ERROR_MESSAGE);
                }
            } catch (HeadlessException Ex){
                
            } catch (IOException ex) {

            }
        });
        AddUser.setBounds(170, 40, 300,50);
        AddUser.setText("Agregar o activar usuario");
        AddUser.setFocusable(false);
        
        add(AddUser);
        
        JButton RemoveUser = new JButton();
        
        RemoveUser.addActionListener((ActionEvent Ev) ->{
            try {
                String NeoUser = JOptionPane.showInputDialog(this, "Ingrese el nombre de usuario: ");
                if (!NeoUser.isBlank()){
                    if (PSN.RemoveUser(NeoUser)){
                        JOptionPane.showMessageDialog(this, "¡Se ha eliminado el usuario exitosamente!", "Agregar usuario", JOptionPane.INFORMATION_MESSAGE);
                    } else JOptionPane.showMessageDialog(this, "¡Ha ocurrido un problema intentando eliminar el usuario!", "Agregar usuario", JOptionPane.ERROR_MESSAGE);
                }
            } catch (HeadlessException Ex){
                
            } catch (IOException ex) {

            }
        });
        RemoveUser.setBounds(170, AddUser.getY() + AddUser.getHeight() + 40, 300,50);
        RemoveUser.setText("Eliminar usuario");
        RemoveUser.setFocusable(false);
        
        add(RemoveUser);
        
        JButton AddTrophyBTN = new JButton();
        
        AddTrophyBTN.addActionListener((ActionEvent Ev) ->{
            Main_Menu.TP.Reveal(true);
            Main_Menu.UsIn.Reveal(false);
            Main_Menu.MP.setVisible(false);
        });
        AddTrophyBTN.setBounds(170, RemoveUser.getY() + RemoveUser.getHeight() + 40, 300,50);
        AddTrophyBTN.setText("Agregar trofeo a usuario");
        AddTrophyBTN.setFocusable(false);
        
        add(AddTrophyBTN);
        
        JButton UserInfo = new JButton();
        
        UserInfo.addActionListener((ActionEvent Ev) ->{
            Main_Menu.TP.Reveal(false);
            Main_Menu.UsIn.Reveal(true);
            Main_Menu.MP.setVisible(false);
        });
        UserInfo.setBounds(170, AddTrophyBTN.getY() + AddTrophyBTN.getHeight() + 40, 300,50);
        UserInfo.setText("Revisar informacion de usuario");
        UserInfo.setFocusable(false);
        
        add(UserInfo);
    }
    
}
class TrophyPanel extends JPanel{
    private final PSNUser PSN;
    
    public TrophyPanel(PSNUser PSN){
        this.PSN = PSN;
        
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);
        setBounds(0,0,350,305);
        setSize(650,463);
        
        Game = new JTextField();
        TrophyType = new JComboBox();
        TrophyName = new JTextField();
        
        setBTNs();
        setTXTArea();
        setTropyBox();
    }
    
    private void setBTNs(){
        JButton Regresar = new JButton();
        
        Regresar.addActionListener((ActionEvent Ev)->{
            Main_Menu.TP.Reveal(false);
            Main_Menu.MP.setVisible(true);
            Main_Menu.UsIn.Reveal(false);
        });
        Regresar.setBounds(10, getHeight() - 55, getWidth()/3, 40);
        Regresar.setFocusable(false);
        Regresar.setText("Regresar");
        
        add(Regresar);
        
        JButton Registrar = new JButton();
        
        Registrar.addActionListener((ActionEvent Ev)->{
            String GameTrophy = this.Game.getText();
            String TrophyNameSave = this.TrophyName.getText();
            if (!GameTrophy.isBlank() && !TrophyNameSave.isBlank()){
                try {
                    String Username = JOptionPane.showInputDialog(this, "Ingrese el usuario al que desea agreagar este usuario: ");
                    if (!Username.isBlank()){
                        if (PSN.AddTrophyTo(Username, GameTrophy, TrophyNameSave, 
                            switch (TrophyType.getSelectedIndex()){
                                case 0 -> Trophy.Platino;
                                case 1 -> Trophy.Oro;
                                case 2 -> Trophy.Plata;
                                default -> Trophy.Bronce;
                            })){
                            JOptionPane.showMessageDialog(this, "Se ha agregado el trofeo exitosamente!");
                            this.Game.setText("");
                            this.TrophyName.setText("");
                            this.TrophyType.setSelectedIndex(0);
                        } else JOptionPane.showMessageDialog(this, "No se ha podido agregar el trofeo");
                    }
                } catch (Exception Ex){}
            }
        });
        Registrar.setBounds(getWidth() - getWidth()/3 - 30, Regresar.getY(), getWidth()/3, 40);
        Registrar.setFocusable(false);
        Registrar.setText("Agregar");
        
        add(Registrar);
    }
    
    private void setTXTArea(){
        Game.setBounds(10, 25, getWidth() - 40, 40);
        JLabel GameTXT = new JLabel("Nombre del juego: ");
        
        GameTXT.setBounds(10, Game.getY() - 30, Game.getWidth(), Game.getHeight());
        add(GameTXT);
        add(Game);
        
        TrophyName.setBounds(Game.getX(), Game.getY() + Game.getHeight() + 40, Game.getWidth(), Game.getHeight());
        JLabel TrophyTXT = new JLabel("Nombre del trofeo: ");
        
        TrophyTXT.setBounds(TrophyName.getX(), TrophyName.getY() - 30, TrophyName.getWidth(), TrophyName.getHeight());
        add(TrophyTXT);
        add(TrophyName);
    }
    
    private void setTropyBox(){
        TrophyType.setBounds(getWidth()/2, TrophyName.getY() + TrophyName.getHeight() + 20, getWidth()/2 - 20, TrophyName.getHeight());
        TrophyType.setModel(new DefaultComboBoxModel(new String[] {"Platino", "Oro", "Plata", "Bronce"}));
        JLabel TypeTXT = new JLabel("Tipo del trofeo: ");
        
        TypeTXT.setBounds(10, TrophyType.getY(), TrophyType.getWidth(), TrophyType.getHeight());
        TypeTXT.setHorizontalAlignment(JLabel.CENTER);
        add(TypeTXT);
        add(TrophyType);
    }
    
    public void Reveal(boolean Reveal){
        setVisible(Reveal);
        if (Reveal){
            this.Game.setText("");
            this.TrophyName.setText("");
            this.TrophyType.setSelectedIndex(0);            
        }
    }
    
    //-- SWING ELEMENT --
    private final JTextField Game;
    private final JComboBox TrophyType;
    private final JTextField TrophyName;
    //-- SWING ELEMENT --
}
class UserInfo extends JPanel{
    private final PSNUser PSN;
    
    public UserInfo(PSNUser PSN){
        this.PSN = PSN;
        Desc = new JTextArea();
        
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);
        setBounds(0,0,350,305);
        setSize(650,462);
        
        setBTNS();
        setDescBox();
    }
    
    private void setBTNS(){
        JButton Regresar = new JButton();
        
        Regresar.addActionListener((ActionEvent Ev)->{
            Main_Menu.TP.Reveal(false);
            Main_Menu.UsIn.Reveal(false);
            Main_Menu.MP.setVisible(true);
        });
        Regresar.setBounds(15, getHeight() - 55, getWidth()-40, 40);
        Regresar.setFocusable(false);
        Regresar.setText("Regresar");
        
        add(Regresar);
    }
    
    private void setDescBox(){
        Desc = new JTextArea();
        
        Desc.setEditable(false);
        Desc.setBounds(10, 10, getWidth() - 35, getHeight() - 105);
        JScrollPane Skroll = new JScrollPane(Desc);
        Skroll.setBounds(Desc.getBounds());
        add(Skroll);
        
        UserInfo = new JLabel();

        UserInfo.setBounds(Desc.getX(), Desc.getY() + Desc.getHeight(), Desc.getWidth(), 40);
        add(UserInfo);
    }
    
    public void Reveal(boolean Reveal){
        setVisible(Reveal);
        if (Reveal){
            try{
                String Username = JOptionPane.showInputDialog(this, "Ingrese el nombre del usuario que desea buscar: ");
                if (!Username.isBlank()){
                    String Info = PSN.PlayerInfo(Username);
                    Desc.setText(Info);
                    if (!Info.isEmpty()) {
                        UserInfo.setText(Username + " cuenta con " + PSN.getTrophyCant() + " trofeo(s) - Pts: " + PSN.getUserPoints());
                    } else {
                        JOptionPane.showMessageDialog(this, "El usuario que busca no cuenta con trofeos!");
                      UserInfo.setText("");
                    }
                }
            } catch (Exception Ex){}
        }
    }
    
    //-- SWING ELEMENTS --
    private JTextArea Desc;
    private JLabel UserInfo;
    //-- SWING ELEMENTS --
}