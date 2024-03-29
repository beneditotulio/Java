import java.util.Arrays;

public class Main {

    // Retorna a união dos conjuntos A e B (elementos únicos em ambos os conjuntos)
    public static int[] uniao(int[] conjuntoA, int[] conjuntoB) {
        int[] temp = new int[conjuntoA.length + conjuntoB.length];
        int tamanho = 0;
        for (int elemento : conjuntoA) {
            if (!contem(temp, elemento, tamanho)) {
                temp[tamanho++] = elemento;
            }
        }
        for (int elemento : conjuntoB) {
            if (!contem(temp, elemento, tamanho)) {
                temp[tamanho++] = elemento;
            }
        }
        return Arrays.copyOf(temp, tamanho);
    }

    // Retorna a interseção dos conjuntos A e B (elementos presentes em ambos os conjuntos)
    public static int[] interseccao(int[] conjuntoA, int[] conjuntoB) {
        int[] temp = new int[Math.min(conjuntoA.length, conjuntoB.length)];
        int tamanho = 0;
        for (int elementoA : conjuntoA) {
            for (int elementoB : conjuntoB) {
                if (elementoA == elementoB && !contem(temp, elementoA, tamanho)) {
                    temp[tamanho++] = elementoA;
                    break;
                }
            }
        }
        return Arrays.copyOf(temp, tamanho);
    }

    // Retorna a diferença entre os conjuntos A e B (elementos que estão em A, mas não em B)
    public static int[] diferenca(int[] conjuntoA, int[] conjuntoB) {
        int[] temp = new int[conjuntoA.length];
        int tamanho = 0;
        for (int elementoA : conjuntoA) {
            if (!contem(conjuntoB, elementoA)) {
                temp[tamanho++] = elementoA;
            }
        }
        return Arrays.copyOf(temp, tamanho);
    }

    // Retorna o complemento do conjunto em relação ao universo especificado
    public static int[] complemento(int[] conjunto, int[] universo) {
        return diferenca(universo, conjunto);
    }

    // Retorna o produto cartesiano dos conjuntos A e B
    public static int[][] produtoCartesiano(int[] conjuntoA, int[] conjuntoB) {
        int[][] resultado = new int[conjuntoA.length * conjuntoB.length][2];
        int pos = 0;
        for (int elementoA : conjuntoA) {
            for (int elementoB : conjuntoB) {
                resultado[pos][0] = elementoA;
                resultado[pos][1] = elementoB;
                pos++;
            }
        }
        return resultado;
    }

    // Verifica se o conjunto A é um subconjunto do conjunto B
    public static boolean ehSubconjunto(int[] conjuntoA, int[] conjuntoB) {
        for (int elemento : conjuntoA) {
            if (!contem(conjuntoB, elemento)) {
                return false;
            }
        }
        return true;
    }

    // Retorna o conjunto das partes do conjunto especificado
    public static int[][] conjuntoDasPartes(int[] conjunto) {
        int numSubconjuntos = (int) Math.pow(2, conjunto.length);
        int[][] partes = new int[numSubconjuntos][];
        for (int i = 0; i < numSubconjuntos; i++) {
            int tamanhoSubconjunto = Integer.bitCount(i);
            partes[i] = new int[tamanhoSubconjunto];
            for (int j = 0, pos = 0; j < conjunto.length; j++) {
                if ((i & (1 << j)) != 0) {
                    partes[i][pos++] = conjunto[j];
                }
            }
        }
        return partes;
    }

    // Retorna a diferença simétrica entre os conjuntos A e B
    public static int[] diferencaSimetrica(int[] conjuntoA, int[] conjuntoB) {
        int[] difA = diferenca(conjuntoA, conjuntoB);
        int[] difB = diferenca(conjuntoB, conjuntoA);
        return uniao(difA, difB);
    }

    // Método auxiliar para verificar se um elemento está contido em um array até um determinado índice
    private static boolean contem(int[] array, int elemento, int ateIndice) {
        for (int i = 0; i < ateIndice; i++) {
            if (array[i] == elemento) {
                return true;
            }
        }
        return false;
    }

    // Método auxiliar para verificar se um elemento está contido em um array
    private static boolean contem(int[] array, int elemento) {
        for (int valor : array) {
            if (valor == elemento) {
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        // Definindo alguns conjuntos de exemplo
        int[] conjuntoA = {1, 2, 3, 4};
        int[] conjuntoB = {3, 4, 5, 6};
        int[] universo = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Teste de união
        System.out.println("União: " + Arrays.toString(uniao(conjuntoA, conjuntoB)));

        // Teste de interseção
        System.out.println("Interseção: " + Arrays.toString(interseccao(conjuntoA, conjuntoB)));

        // Teste de diferença
        System.out.println("Diferença (A - B): " + Arrays.toString(diferenca(conjuntoA, conjuntoB)));

        // Teste de complemento
        System.out.println("Complemento de A no universo: " + Arrays.toString(complemento(conjuntoA, universo)));

        // Teste de produto cartesiano
        System.out.println("Produto Cartesiano (A x B): ");
        int[][] produto = produtoCartesiano(conjuntoA, conjuntoB);
        for (int[] par : produto) {
            System.out.println(Arrays.toString(par));
        }

        // Teste de subconjunto
        System.out.println("Conjunto A é subconjunto de B? " + ehSubconjunto(conjuntoA, conjuntoB));

        // Teste de conjunto das partes
        System.out.println("Conjunto das Partes de A: ");
        int[][] partes = conjuntoDasPartes(conjuntoA);
        for (int[] subconjunto : partes) {
            System.out.println(Arrays.toString(subconjunto));
        }

        // Teste de diferença simétrica
        System.out.println("Diferença Simétrica entre A e B: " + Arrays.toString(diferencaSimetrica(conjuntoA, conjuntoB)));
    }

}
