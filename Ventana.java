

//import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;



public class Ventana extends JFrame implements ActionListener {
 
private Container contenedor;
private JButton botonSalir, botonExaminar, BotonAnalizar;
private JLabel labelTitulo, labelExaminar, labelResultado;
private JTextField campoRtArchivo;
private JList list;
private DefaultListModel model;
private Lectura lector;
private Logica logica;

public Ventana(){
  
  iniciarComponentes();
  
  setTitle("Taller III DSII");
  
  setSize(600,480);
  
  setLocationRelativeTo(null);
}
 
 
private void iniciarComponentes() {
  contenedor=getContentPane();
 
  contenedor.setLayout(null);
  
  model = new DefaultListModel();
  list = new JList(model);
  
  JScrollPane pane = new JScrollPane(list);
  pane.setBounds(20, 150, 560, 230);
  
  botonSalir= new JButton();
  botonSalir.setText("Salir");
  botonSalir.setBounds(470, 400, 110, 25);
  botonSalir.addActionListener(this);
  
  botonExaminar = new JButton();
  botonExaminar.setText("Examinar");
  botonExaminar.setBounds(340, 70, 110, 25);
  botonExaminar.addActionListener(this);
  
  BotonAnalizar = new JButton();
  BotonAnalizar.setText("Analizar");
  BotonAnalizar.setBounds(460, 70, 110, 25);
  BotonAnalizar.addActionListener(this);
  
  labelExaminar = new JLabel();
  labelExaminar.setText("Abrir archivo :");
  labelExaminar.setBounds(20, 70, 140, 25);
  
  labelTitulo= new JLabel();
  labelTitulo.setText("Listado puntos ingresados");
  labelTitulo.setBounds(220, 20, 280, 23);
  
  labelResultado = new JLabel();
  labelResultado.setText("Resultados del analisis");
  labelResultado.setBounds(20, 110, 200, 25);
   
  campoRtArchivo = new JTextField();
  campoRtArchivo.setBounds(160, 70, 160, 25);
  campoRtArchivo.setEditable(false);
  campoRtArchivo.setBackground(Color.white);
  
  contenedor.add(labelTitulo);
  contenedor.add(botonSalir);
  contenedor.add(botonExaminar);
  contenedor.add(BotonAnalizar);
  contenedor.add(labelExaminar);
  contenedor.add(campoRtArchivo);
  contenedor.add(labelResultado);
  contenedor.add(pane);
          
}

public File selector(){
  
      JFileChooser file =  new JFileChooser();
      file.showOpenDialog(this);
      
      File abre = file.getSelectedFile();
      
      campoRtArchivo.setText(abre.getName());
      
      return abre;
  }

public void mostrarResultados(ArrayList resultado){

    for(int i = 0; i < resultado.size(); i++){
        
        model.addElement(resultado.get(i));
    
    }

}
  
@Override
  public void actionPerformed(ActionEvent evento) {
    
      if (evento.getSource()==botonSalir){
 
          dispose();
      }else if(evento.getSource() == botonExaminar){
        
          lector = new Lectura();
          lector.leer(selector());
          
          
      
      }else if(evento.getSource() == BotonAnalizar){
      
          int x1 = Integer.valueOf((String) lector.getCircunferencia().get(0));
          int y1 = Integer.valueOf((String) lector.getCircunferencia().get(1));
          int r1 = Integer.valueOf((String) lector.getCircunferencia().get(2));
          int x2 = Integer.valueOf((String) lector.getCircunferencia().get(3));
          int y2 = Integer.valueOf((String) lector.getCircunferencia().get(4));
          int r2 = Integer.valueOf((String) lector.getCircunferencia().get(5));
          int[][] points = lector.getPuntos();

          logica = new Logica(x1, y1, r1, x2, y2, r2, points);
          
          ArrayList arr = logica.resultExport();

          mostrarResultados(arr);
      }      
    }

 public static void main(String args[]){
     Ventana vent = new Ventana();
     vent.setVisible(true);
     vent.setDefaultCloseOperation(EXIT_ON_CLOSE);
 }
}
