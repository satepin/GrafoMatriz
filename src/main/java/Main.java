public class Main {
  public static void main(String[] args) {
    boolean loop = true;
    int menu = 0;
    GrafoMatriz grafo = new GrafoMatriz();
    Entrada scanner = new Entrada();
    System.out.println("Crear nuevo grafo:\n1.Grafo con datos\n2.Grafo vacio\nIngrese cualquier otro valor para salir");
    menu = scanner.intIn();
    switch (menu) {
      case 1:
        System.out.println("Generando grafo con datos");
        grafo.autoCarga();
      case 2:
        do {
          grafo.imprimirMatriz(); //la matriz se mostrara automaticamente tras cualquier operacion. esta solo se imprime hasta las dimensiones cargadas en esta, por lo que comenzara con un unico dato 0
          System.out.println("\n\n1.Crear vertice\n2.Crear arco\n3.Buscar vertice\n4.Eliminar Vertice\n5.Eliminar arco\n6.Exploracion en anchura\n7.Salir");
          menu = scanner.intIn();
          switch (menu) {
            case 1:
              grafo.crearVertice(scanner.intIn());
              break;
            case 2:
              grafo.crearArco(scanner.intIn(), scanner.intIn());
              break;
            case 3:
              grafo.busqueda(scanner.intIn());
              break;
            case 4:
              grafo.borrarVertice(scanner.intIn());
              break;
            case 5:
              grafo.borrarArco(scanner.intIn(), scanner.intIn());
              break;
            case 6:
              grafo.explorarAnchura();
              break;
            case 7:
              loop = false;
              break;
            default:
              System.out.println("Ingrese una opcion valida");
          }
        } while (loop);
        break;
      default:
        break;
    }
    scanner.close();
    System.out.println("Cerrando programa. . .");
  }
}