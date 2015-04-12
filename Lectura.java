import java.io.*;
import java.util.*;


public class Lectura 
{
    ArrayList circunferencia, coordenadas;
    int [][] puntos;
    
    public void setCircunferencia(ArrayList Circunferencia)
    {
        this.circunferencia = Circunferencia;
    }
    
    public ArrayList getCircunferencia()
    {
        return circunferencia;
    }
    
    public void setCoordenadas(ArrayList Coordenadas)
    {
        this.coordenadas = Coordenadas;
    }
    
    public ArrayList getCoordenadas()
    {
        return coordenadas;
    }
    
    public void setPuntos(int [][] puntos)
    {
        this.puntos = puntos;
    }
    
    public int [][] getPuntos()
    {
        return puntos;
    }
    
    public void leer (File file)
    {
        File archivo;
        FileReader fileReader;
        BufferedReader bufferedReader;
        String linea;
    
        try
        {
            archivo = file;
            fileReader = new FileReader (archivo);
            bufferedReader = new BufferedReader(fileReader);
            circunferencia = new ArrayList();
            coordenadas = new ArrayList();
            
            for(int i = 0; (linea = bufferedReader.readLine()) != null; i++)
            {
                if (i == 0) 
                {
                    circunferencia.add(linea);
                }
                
                else 
                {
                    coordenadas.add(linea);
                }
            }
        }
        
        catch(FileNotFoundException fnfe)
        {
            System.err.println(fnfe.getMessage());
        }
        
        catch(IOException ioe)
        {
            System.err.println(ioe.getMessage());
        }
        
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        Tokenizer(circunferencia);
        Tokenizer(coordenadas);
        Puntos(coordenadas);
    }
    
    public void Tokenizer (ArrayList lista)
    {
        String lineas, valor;
        StringTokenizer token;
        ArrayList cloneCircunferencia, cloneCoordenadas;
        
        if (lista.size() == 1) 
        {
            lineas = (String) lista.get(0);
            token = new StringTokenizer(lineas, " ,;()");
            cloneCircunferencia = new ArrayList();
            
            while(token.hasMoreTokens())
            {
                valor = (String) token.nextElement();
                cloneCircunferencia.add(valor);
            }
            
            setCircunferencia(cloneCircunferencia);
        }
        
        else 
        {   
            cloneCoordenadas = new ArrayList();
            
            for (int j = 0; j < lista.size(); j++)
            {
                lineas = (String) lista.get(j);
                token = new StringTokenizer(lineas, " ,()");
                
                while(token.hasMoreTokens())
                {
                    valor = (String) token.nextElement();
                    cloneCoordenadas.add(valor);
                }
            }
            
            setCoordenadas(cloneCoordenadas);
        }
    }
    
    public void Puntos (ArrayList cloneCoordenadas) 
    {
        int puntoX, puntoY, posicion;
        int [][] clonePuntos;
        
        clonePuntos = new int [cloneCoordenadas.size()/2][2];
        posicion = 0;
        
        for (int x = 0; x < (cloneCoordenadas.size() - 1); x += 2) 
        {
            puntoX = Integer.valueOf((String)  cloneCoordenadas.get(x));
            puntoY = Integer.valueOf((String)  cloneCoordenadas.get(x + 1));
            
            clonePuntos [posicion][0] = puntoX;
            clonePuntos [posicion][1] = puntoY;
            
            posicion++;
        }
        
        setPuntos(clonePuntos);
    }
}
