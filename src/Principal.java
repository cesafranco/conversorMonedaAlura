import java.io.IOException;
import java.util.Scanner;

    // Clase principal del programa
    public class Principal {
        public static void main(String[] args) throws IOException, InterruptedException {

            // Crear un objeto Scanner para leer la entrada del usuario
            Scanner teclado = new Scanner(System.in);

            // Definir el menú que se presentará al usuario
            String menu = """
                **************************************
                Conversor de monedas
                
                1 - Dólar => Peso Argentino.
                2 - Peso Argentino => Dólar.
                3 - Dólar => Real Brasileño.
                4 - Real Brasileño => Dólar.
                5 - Dólar => Peso Colombiano.
                6 - Peso Colombiano => Dólar.
                7 - Soles PEN => Dólar.
                8 - Dólar => Soles PEN.
                9 - Salir.
                **************************************
                """;


            int opcion = 0;
            ConvertirMoneda convertidor = new ConvertirMoneda();
            double cantidadConvertida = 0.0;

            while (opcion != 9) {
                System.out.println(menu);
                System.out.println("Elije una opción para continuar: ");
                // Leer la opción elegida por el usuario
                opcion = teclado.nextInt();

                switch (opcion) {
                    case 1, 2, 3, 4, 5, 6, 7, 8:
                        System.out.println("Ingrese la cantidad a convertir:");
                        double cantidad = teclado.nextDouble();
                        cantidadConvertida = convertidor.convertirMoneda(cantidad, String.valueOf(opcion));
                        System.out.println("El valor " + cantidad + " corresponde a la conversion final de: " + cantidadConvertida);
                        break;
                    case 9:
                        System.out.println("finalizando el programa Hasta Luego ......");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                        break;
                }
            }
        }
    }


