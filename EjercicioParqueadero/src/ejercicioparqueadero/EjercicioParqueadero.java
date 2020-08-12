package EjercicioParqueadero;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class EjercicioParqueadero {

    public static void main(String[] args) throws ParseException {
        //Declaracion de variables y formatos de hora
        float tarifa;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
        SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
        double totalVentas = 0;
        //Solicitud tarifa
        do {
            try {
                tarifa = Float.parseFloat(JOptionPane.showInputDialog("Ingrese tarifa"));
                if (tarifa > 0) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Valor incorrecto");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Valor incorrecto");
            }
        } while (true);
        //Proceso
        while (true) {
            String valor = JOptionPane.showInputDialog("1. Ingrese la hora de entrada en formato 24 horas (Ejemplo: 15:15) \n2. Ingrese cero para salir");
            String ciclo = valor;
            Date Actual = new Date();
            String TiempoSalida = (formato.format(Actual));
            Date tiempoSalida = formato.parse(formato.format(Actual));
            if (ValidarHora(valor, TiempoSalida) == 0 && !"0".equals(valor)) {
                String horaEntrada = valor;
                Date tiempoEntrada = formato.parse(horaEntrada);

                int cantidas_de_horas_Cobradas = CantidadHoras(tiempoEntrada, tiempoSalida);

                double valorApagar = CantidadPagar(cantidas_de_horas_Cobradas, tarifa);

                totalVentas += valorApagar;
                Date FechaActual = new Date();
                System.out.println("-----------------------" + "\nFecha: " + formato2.format(FechaActual) + "\nHora entrada: " + formato.format(tiempoEntrada) + "\nHora Salida: " + formato.format(tiempoSalida) + "\nHoras permanencia: " + cantidas_de_horas_Cobradas + "\nValor a pagar: $" + valorApagar + "\nVentas: $" + totalVentas);
            } else {
                if ("0".equals(ciclo)) {
                    JOptionPane.showMessageDialog(null, "Usted Salió");
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Hora incorrecta");
                }
            }
        }
    }

    static public int ValidarHora(String hora, String TiempoSalida) throws ParseException {
        int respuesta = 0;
        //Verificar que el formato de dos puntos sea correcto
        try {
            if (hora.split(":").length != 2) {
                respuesta = 1;
            } else {
                String arreglo[] = hora.split(":");
                for (int i = 0; i < arreglo.length; i++) {
                    try {
                        int valor = Integer.parseInt(arreglo[i]);
                    } catch (Exception e) {
                        respuesta = 1;
                    }
                }
            }
        } catch (Exception e) {
        }
        //verificar que los numeros de hora no excedan los limites
        try {
            if (Integer.parseInt(hora.split(":")[0]) > 23 || Integer.parseInt(hora.split(":")[0]) < 0) {
                respuesta = 1;
            }
            if (Integer.parseInt(hora.split(":")[1]) > 59 || Integer.parseInt(hora.split(":")[1]) < 0) {
                respuesta = 1;
            }
        } catch (Exception e) {
        }
        //Verificar que la hora de salida no sea menor a la de entrada
        try {
            SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
            Date TiempoEntrada = formato.parse(hora);
            Date Horacero = formato.parse("00:00");
            Date tiempoSalida = formato.parse(TiempoSalida);
            long DiferenciaSalida = tiempoSalida.getTime() - Horacero.getTime();
            long DiferenciaEntrada = TiempoEntrada.getTime() - Horacero.getTime();
            if (DiferenciaSalida < DiferenciaEntrada) {
                respuesta = 1;
            }
        } catch (Exception e) {
        }
        return respuesta;
    }

    static public int CantidadHoras(Date tiempoEntrada, Date tiempoSalida) {
        int cantidas_de_horas_Cobradas = 0;
        long milliseconds = tiempoSalida.getTime() - tiempoEntrada.getTime();
        int seconds = (int) (milliseconds / 1000) % 60;
        int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
        int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);

        if (minutes != 0) {
            cantidas_de_horas_Cobradas = hours + 1;
        } else {
            cantidas_de_horas_Cobradas = hours;
        }
        return cantidas_de_horas_Cobradas;
    }

    static public double CantidadPagar(int cantidas_de_horas_Cobradas, float tarifa) {
        double valorApagar = 0;
        if (cantidas_de_horas_Cobradas > 1 && cantidas_de_horas_Cobradas < 5) {
            double descuento = 0.05 * (cantidas_de_horas_Cobradas - 1);
            valorApagar = cantidas_de_horas_Cobradas * tarifa * (1 - descuento);
        } else {
            if (cantidas_de_horas_Cobradas >= 5) {
                valorApagar = cantidas_de_horas_Cobradas * tarifa * (0.8);
            } else {
                valorApagar = tarifa;
            }
        }
        return valorApagar;
    }

    static public double[] CantidadPagarVector( float tarifa, int cantidad) {
        double[] valorApagar = new double[cantidad];
        for (int i = 1; i < cantidad + 1; i++) {
            if (i > 1 && i < 5) {
                double descuento = 0.05 * (i - 1);
                valorApagar[i-1] = i * tarifa * (1 - descuento);
            } else {
                if (i >= 5) {
                    valorApagar[i-1] = i * tarifa * (0.8);
                } else {
                    valorApagar[i-1] = tarifa;
                }
            }
        }
        return valorApagar;
    }

}
