
package group.chatting.application;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;


public class UserOne implements ActionListener, Runnable {
    
    JTextField text;
    JPanel a1;
    static Box vertical = Box.createVerticalBox();
    static JFrame f = new JFrame();
    static DataOutputStream dout;
    
    BufferedReader reader;
    BufferedWriter writer;
    String name = "Kaleen Bhaiya";
    
    UserOne(){
        
        f.setLayout(null);
        
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        p1.setBounds(0, 0, 450, 70);
        p1.setLayout(null);
        f.add(p1);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5, 20, 25,25);        
        p1.add(back);
        
        back.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
            
                System.exit(0);
            
            }
        
        
        });
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/mirzapur.png"));
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40, 10, 50,50);        
        p1.add(profile);
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(300, 20, 30,30);        
        p1.add(video);
        
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel phone = new JLabel(i12);
        phone.setBounds(360, 20, 30,30);        
        p1.add(phone);
        
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel morevert = new JLabel(i15);
        morevert.setBounds(420, 20, 10,25);        
        p1.add(morevert);
        
        JLabel name = new JLabel("Mirzapur");
        name.setFont(new Font("System", Font.BOLD, 15));
        name.setForeground(Color.white);
        name.setBounds(110, 15, 100, 18);
        p1.add(name);
        
        JLabel status = new JLabel("Kaleen, Guddu, Sweety, Bablu, Shukla");
        status.setFont(new Font("System", Font.BOLD, 13));
        status.setForeground(Color.white);
        status.setBounds(110, 35, 160, 18);
        p1.add(status);
        
        a1 = new JPanel();
        a1.setBounds(5, 75, 440, 570);
        a1.setBackground(Color.white);
        f.add(a1);
        
        text = new JTextField();
        text.setBounds(5, 655, 310, 40);
        text.setFont(new Font("SAN_SERIF" , Font.PLAIN , 15));
        f.add(text);
        
        JButton send = new JButton("Send");
        send.setBounds(320, 655, 123, 40);
        send.setBackground(Color.green);
        send.setForeground(Color.white);
        send.setFont(new Font("SAN_SERIF", Font.BOLD, 15));
        send.addActionListener(this);
        f.add(send);
        
        f.setSize(450, 700);
        f.setLocation(20, 50);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.white);
        f.setVisible(true);
        
        try{
            Socket socket = new Socket("localhost", 2003);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            
        
        
        }catch(Exception e){
            e.printStackTrace();
        
        }
        
        
        
    
      
    }
    
    
    
    public void actionPerformed(ActionEvent ae){
        try{String out = "<html><p>" + name + "</p><p>" + text.getText() + "</p><html>";
       
        JPanel p2 = formatLabel(out);
      
        
        a1.setLayout(new BorderLayout());
        
        JPanel right = new JPanel(new BorderLayout());
        right.setBackground(Color.white);
        right.add(p2, BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));
        
        a1.add(vertical, BorderLayout.PAGE_START);
        
        try{
            writer.write(out);
            writer.write("\r\n");
            writer.flush();
        
        }catch(Exception e){
            e.printStackTrace();
        
        }
        
        text.setText("");
        
        f.repaint();
        f.invalidate();
        f.validate();
        
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        
    
    
    }
    public static JPanel formatLabel(String out){
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(110, 190, 240));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(0, 15, 0, 50));
        
        panel.add(output);
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        
        panel.add(time);
        
        return panel;
    
    }
    
    public void run(){
        try{
            String msg ="";
            while(true){
                msg = reader.readLine();
                if(msg.contains(name)){
                    continue;
                }
                JPanel panel = formatLabel(msg);
                
                JPanel left = new JPanel(new BorderLayout());
                left.setBackground(Color.white);
                left.add(panel, BorderLayout.LINE_START);
                vertical.add(left);
                
                a1.add(vertical, BorderLayout.PAGE_START);
                
                f.repaint();
                f.invalidate();
                f.validate();
            }
        
        }catch(Exception e){
            e.printStackTrace();
        
        }
    
    }
    
    public static void main (String[] args){
        UserOne one = new UserOne();
        Thread t1 = new Thread(one );
        t1.start();
        
        
    
    }
    
}
