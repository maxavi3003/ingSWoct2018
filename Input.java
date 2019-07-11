import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Calendar;
import javax.swing.*;

public class Input extends JFrame{

    static JTextField t1;
    static JTextField t2;
    static JTextField t3;
    
	public Input()
	{
        JFrame frame = new JFrame("Cálculo de edad");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        agregarControles(frame.getContentPane());
 
        Insets insets = frame.getInsets();
        frame.setSize(300 + insets.left + insets.right,
                      200 + insets.top + insets.bottom);
        frame.setVisible(true);
	}
	
	public static void agregarControles(Container pane) {
        pane.setLayout(null);
 
        JLabel l1 = new JLabel("Ingrese el día:");
        pane.add(l1);
        Insets insets = pane.getInsets();
        Dimension size = l1.getPreferredSize();
        l1.setBounds(25 + insets.left, 5 + insets.top,
                     size.width, size.height);
        
        t1 = new JTextField();
        pane.add(t1);
        insets = pane.getInsets();
        size = t1.getPreferredSize();
        t1.setBounds(125 + insets.left, 5 + insets.top,
                     100, size.height);
        
        JLabel l2 = new JLabel("Ingrese el mes:");
        pane.add(l2);
        insets = pane.getInsets();
        size = l2.getPreferredSize();
        l2.setBounds(25 + insets.left, 35 + insets.top,
                     size.width, size.height);
        
        t2 = new JTextField();
        pane.add(t2);
        insets = pane.getInsets();
        size = t2.getPreferredSize();
        t2.setBounds(125 + insets.left, 35 + insets.top,
                     100, size.height);
        
        JLabel l3 = new JLabel("Ingrese el año:");
        pane.add(l3);
        insets = pane.getInsets();
        size = l3.getPreferredSize();
        l3.setBounds(25 + insets.left, 65 + insets.top,
                     size.width, size.height);
        
        t3 = new JTextField();
        pane.add(t3);
        insets = pane.getInsets();
        size = t3.getPreferredSize();
        t3.setBounds(125 + insets.left, 65 + insets.top,
                     100, size.height);
        
        
        JButton b1 = new JButton("Calcular");
        b1.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
        	  calcularEdad();      
          }
        });
        pane.add(b1);
        insets = pane.getInsets();
        size = b1.getPreferredSize();
        b1.setBounds(125 + insets.left, 105 + insets.top,
                     size.width, size.height);
    }
	
    public static void calcularEdad(){
        
        int año = Integer.parseInt(t3.getText());
        int mes = Integer.parseInt(t2.getText());
        mes = mes - 1;
        int dia = Integer.parseInt(t1.getText());    
        
        Calendar hoy = Calendar.getInstance();
        hoy.setTimeInMillis(System.currentTimeMillis());
        
        Calendar birthDay = Calendar.getInstance();
        birthDay.set(año, mes, dia);
        
        int edadAños = hoy.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
        
        int edadMeses = 0;

        if(hoy.get(Calendar.MONTH) < birthDay.get(Calendar.MONTH)){
        	edadMeses = 12 - (birthDay.get(Calendar.MONTH) - hoy.get(Calendar.MONTH));
            edadAños = edadAños - 1;
        }else{
        	edadMeses =  hoy.get(Calendar.MONTH) - birthDay.get(Calendar.MONTH);
        }
        
        String resultado = "La edad calculada es: "+ edadAños + " años con "+edadMeses + " meses";
        escribirArchivo(resultado);
        JOptionPane.showMessageDialog(null,resultado);
        
    }
    
    public static void escribirArchivo (String texto)
    {
    	File f;
    	f = new File("Resultado.txt");

    	try{
    	FileWriter w = new FileWriter(f);
    	BufferedWriter bw = new BufferedWriter(w);
    	PrintWriter wr = new PrintWriter(bw);	
    	wr.write(texto);
    	wr.close();
    	bw.close();
    	}catch(IOException e){};
	}
}
