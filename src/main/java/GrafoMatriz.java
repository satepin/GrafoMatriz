import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;

public class GrafoMatriz {
  Random random = new Random();
  int vertices;
  int maxVertice;
  Vertice[] Lista;
  int[][] matrizAdy;

  public GrafoMatriz() {
    int i, j;
    maxVertice = 7;
    System.out.println("Limite de vertices por defecto: 7");
    Lista = new Vertice[maxVertice];
    matrizAdy = new int[maxVertice + 1][maxVertice + 1];
    for (i = 0; i < maxVertice + 1; i++) {
      for (j = 0; j < maxVertice + 1; j++) {
        matrizAdy[i][j] = 0;
      }
    }
    for (i = 0; i < maxVertice; i++) {
      Lista[i] = new Vertice();
    }
    vertices = 0;
  }

  public void crearVertice(int nVertice) {
    if (vertices < maxVertice) {
      if (buscarVertice(nVertice) == -1) {
        Lista[vertices].nVertice = nVertice;
        vertices++;
        matrizAdy[vertices][0] = nVertice;
        matrizAdy[0][vertices] = nVertice;

      } else {
        System.out.println("\n\nEl vertice ya existe");
      }
    } else {
      System.out.println("\n\nLa matriz de adyacencia se encuentra llena");
    }

  }

  public int buscarVertice(int nVertice) {
    for (int i = 1; i < maxVertice + 1; i++) {
      if (matrizAdy[i][0] == nVertice) {
        return i;
      }
    }
    return -1;
  }

  public void crearArco(int nVertice1, int nVertice2) {
    int i;
    if (buscarVertice(nVertice1) != -1 && buscarVertice(nVertice2) != -1) {
      i = buscarVertice(nVertice1);
      matrizAdy[i][buscarVertice(nVertice2)] = 1;
      matrizAdy[buscarVertice(nVertice2)][i] = 1; // asumiendo grafo no dirigido
    } else {
      System.out.println("\n\nUno de los vertices no existe");
    }
  }

  public void busqueda(int nVertice) {
    int i;
    if (buscarVertice(nVertice) != -1) {
      i = buscarVertice(nVertice);
      System.out.println("\n\nEl vertice " + Lista[i - 1].nVertice + " tiene arcos con: ");
      for (int j = 1; j < maxVertice + 1; j++) {
        if (matrizAdy[i][j] == 1) {
          System.out.println(Lista[j].nVertice);
        }
      }
    } else {
      System.out.println("\n\nEl vertice no existe");
    }
  }

  public void borrarVertice(int nVertice) {
    int i, j;
    int posicion = buscarVertice(nVertice);
    if (posicion != -1) {
      i = buscarVertice(nVertice + 1);
      for (j = 0; j < vertices; j++) {
        matrizAdy[posicion][j] = 0;
        matrizAdy[j][posicion] = 0;
      }
      for (i = posicion; i < vertices - 1; i++) {
        Lista[i] = Lista[i + 1];
        Lista[i].nVertice = i;
      }
      vertices--;
      retroceso(posicion);
    } else {
      System.out.println("\n\nEl vertice no existe");
    }
  }

  public void retroceso(int pos) {
    for (int i = pos; i < vertices; i++) {
      for (int j = 0; j < vertices; j++) {
        matrizAdy[i][j] = matrizAdy[i + 1][j];
        matrizAdy[j][i] = matrizAdy[j][i + 1];
      }
    }
    System.out.println("\n\nSe han acomodado los vertices");
  }

  public int buscarArco(int nVertice1, int nVertice2) {
    int i, j;
    if (buscarVertice(nVertice1) != -1 && buscarVertice(nVertice2) != -1) {
      i = buscarVertice(nVertice1);
      j = buscarVertice(nVertice2);
      if (matrizAdy[i][j] == 1) {
        return 1;
      } else {
        System.out.println("\n\nEl arco no existe");
        return 0;
      }
    } else {
      System.out.println("\n\nUno de los vertices no existe");
      return 0;
    }
  }

  public void borrarArco(int nVertice1, int nVertice2) {
    if (buscarArco(nVertice1, nVertice2) == 1) {
      int i, j;
      i = buscarVertice(nVertice1);
      j = buscarVertice(nVertice2);
      matrizAdy[i][j] = 0;
      matrizAdy[j][i] = 0;
      System.out
          .println("\n\nSe ha borrado el arco entre " + Lista[i - 1].getNumero() + " y " + Lista[j - 1].getNumero());
    }
  }

  public void imprimirMatriz() {
    int i, j;
    System.out.println("\n====MATRIZ DE ADYACENCIA: tamaño actual " + vertices + " x " + vertices + "====\n");
    for (i = 0; i < vertices + 1; i++) {
      for (j = 0; j < vertices + 1; j++) {
        System.out.print(matrizAdy[i][j] + "|");
      }
      System.out.println();
    }
  }

  public void autoCarga() {
    int i;
    for (i = 0; i < maxVertice; i++) {
      crearVertice(10 + random.nextInt(89));
    }
    for (i = 0; i < vertices * 4; i++) {
      crearArco(Lista[random.nextInt(vertices)].getNumero(), Lista[random.nextInt(vertices)].getNumero());
    }
  }

  public void explorarAnchura() {
    int pos = 1;
    int j;
    int vistos = 0;
    boolean[] visitados = new boolean[vertices + 1];
    Queue<Integer> cola = new LinkedList<Integer>();
    cola.add(matrizAdy[0][1]);
    System.out.println("\n\n" + cola.poll());
    visitados[pos] = true;
    do {
      for (j = 1; j <= vertices; j++) {
        if (matrizAdy[pos][j] == 1 && !visitados[j]) {
          cola.add(matrizAdy[j][0]);
          visitados[j] = true;
          vistos++;
        }
      }
      if (cola.peek() != null) {
        pos = buscarVertice(cola.peek());
      } else {
        for (j = 0; j < vertices; j++) {
          if (!visitados[j]) {
            pos = j;
            j=vertices;
          }
        }
      }
      System.out.println(cola.poll());

    } while (!cola.isEmpty() && vistos < vertices);
  }

}