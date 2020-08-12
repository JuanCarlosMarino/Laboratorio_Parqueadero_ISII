/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioparqueadero;

import EjercicioParqueadero.EjercicioParqueadero;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class EjercicioParqueaderoNGTest {
    @Test
    public void testValidarHora() throws Exception {
        EjercicioParqueadero Operacion = new EjercicioParqueadero();        
        System.out.println("ValidarHora");
        String hora = "14:00";
        String TiempoSalida = "15:30";
        int expResult = 0;
        int result = Operacion.ValidarHora(hora, TiempoSalida);
        assertEquals(result, expResult, "Prueba Individual");        
    }    

    @Test
    public void testCantidadPagar() {
        EjercicioParqueadero Operacion = new EjercicioParqueadero();
        System.out.println("CantidadPagar");        
        float tarifa = 1000;
        double[] expResult = {1000, 1900, 2700, 2400, 4000, 4800, 5600};
        double[] result = Operacion.CantidadPagarVector(tarifa, 7);
        assertEquals(result, expResult);
        
    }
    
}
