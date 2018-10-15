package JogoDaVelha;

import static java.lang.Thread.sleep;

/**
 * Classe que simula um jogo da velha tradicional
 * pode jogar contra computador ou humano
 *
 * @author Rafael Teles Espindola
 */
public class JogoDaVelha {

    /**
     * @param vez      serve para controlar se é a vez do jogdaor 1 ou jogador 2
     * @param matriz   é análogo ao tabuleiro onde se joga o jogo na vida real
     * @param posicao  é a posição escolhida pela IA para se jogar o marcador
     * @param placarP1 conta quantas vezes o Player1 ganhou
     * @param placarP2 conta quantas vezes o Player2 ganhou
     * @param placarPc conta quantas vezes o PlayerPc ganhou
    * */

    private int vez;// vez par -> Jogador1 | vez impar -> Jogador2
    private int posicao;
    private int placarP1;
    private int placarP2;
    private int placarPc;
    private int[][] matriz;

    //metodo util para testes
    public void setPosicaoX(int umaLinha, int umaColuna){
        this.matriz[umaLinha][umaColuna] = -1;
    }

    //metodo util para testes
    public void setPosicaoY(int umaLinha, int umaColuna){
        this.matriz[umaLinha][umaColuna] = 1;
    }

    //metodo util para testes
    public void setMatriz(int [][] umaMatriz){
        this.matriz = umaMatriz;
    }

    /**
    * Cria o tabuleiro do jogo com todas as posições inicialmente com zero
    * */
    public JogoDaVelha(){
        this.matriz = new int[3][3];
    }

    public int getPlacarP1(){
        return placarP1;
    }
    public int getPlacarP2(){
        return placarP2;
    }

    /**
    * Verifica se é a vez do jogador 1
    * @return true se for a vez dele
    * */
    public boolean vezPlayer1(){
        return this.vez % 2 == 0;
    }

    /**
     * Verifica se é a vez do jogador 2
     * @return true se for a vez dele
     * */
    public boolean vezPlayer2(){
        return this.vez % 2 == 1;
    }

    /**
    * Verifica se a posição está vazia para se jogar
    * @return true se a possição estiver vazia
    * */
    public boolean posicaoLivre(int umLinha, int umaColuna){
        return this.matriz[umLinha][umaColuna] == 0;
    }

    /**
    * Analisa a posição indicada pelos parâmetros umaLInha e umaColuna
    * ela é útil para a função "draw" que imprime na tela o tabuleiro
    * @return a string " X " se na possição estiver com -1
    * @return a string " O " se na possição estiver com  1
    * @return a string "   " se na possição estiver com  0
    * */
    public String XouY(int umaLinha, int umaColuna){
        if (this.matriz[umaLinha][umaColuna] == -1) return " X ";
        else if (this.matriz[umaLinha][umaColuna] == 1) return " O ";
        else return "   ";
    }

    /**
    * Apenas desenha na tela o tabuleiro de forma gráfica em modo texto
    * É útil para informar ao jogador de maneira intuitiva como está o jogo
    * */
    public void draw(){

        System.out.println(XouY(0,0) + "\u2502" + XouY(0,1) + "\u2502" + XouY(0,2));
        System.out.println("\u2500\u2500\u2500\u253c\u2500\u2500\u2500\u253c\u2500\u2500\u2500");
        System.out.println(XouY(1,0) + "\u2502" + XouY(1,1) + "\u2502" + XouY(1,2));
        System.out.println("\u2500\u2500\u2500\u253c\u2500\u2500\u2500\u253c\u2500\u2500\u2500");
        System.out.println(XouY(2,0) + "\u2502" + XouY(2,1) + "\u2502" + XouY(2,2));

    }

    /**
    * Na posição indicada pelo jogador é colocado seu marcador
    * Passa a vez para o próximo jogador
    * */
    public void player1Joga(int umaLinha, int umaColuna){
        this.matriz[umaLinha][umaColuna] = -1;
        this.vez++;
    }

    /**
     * Na posição indicada pelo jogador é colocado seu marcador
     * Passa a vez para o próximo jogador
     * */
    public void player2Joga(int umaLinha, int umaColuna){
        this.matriz[umaLinha][umaColuna] = 1;
        this.vez++;
    }

    /**
    * Verifica se ainda há alguma posição livre para poder se jogar
    * independente se ainda há chance de alguém ganhar ou não
    * @return true se não houver mais nenhuma posição livre
    * */
    public boolean deuVelha() {

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (this.matriz[j][i] == 0) return false;
            }
        }
        return true;
    }

    /**
    * Reseta/limpa o tabuleiro, ou seja, todas posições voltam a ser igual a zero
    * Útil para quando se deseja jogar novamente após acabar o jogo atual
    * */
    public void resetGame(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                this.matriz[i][j] = 0;
            }
        }
    }

    /**
    * Verifica se alguém ganhou o jogo
    * O jogo se ganha quando algúem completa uma
    * linha, coluna, ou diagonal com seus marcadores
    *
    * @return true se alguém ganhou
    * */
    public boolean ganhou(){

        //   X|O|O  00|01|02
        //   X|O|X  10|11|12 linha/coluna
        //   O|O|X  20|21|22

        int somaDaLinha = 0;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                somaDaLinha += this.matriz[i][j];
            }
            if (somaDaLinha == -3){
                this.placarP1++;
                return true;
            }else if (somaDaLinha == 3){
                this.placarP2++;
                this.placarPc++;
                return true;
            }
            somaDaLinha = 0;
        }

        int somaDaColuna = 0;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                somaDaColuna += this.matriz[j][i];
            }
            if (somaDaColuna == -3){
                this.placarP1++;
                return true;
            }else if (somaDaColuna == 3){
                this.placarP2++;
                this.placarPc++;
                return true;
            }
            somaDaColuna = 0;
        }

        //ganhou na diagonal
        if((this.matriz[0][0] + this.matriz[1][1] + this.matriz[2][2]) == -3){
            this.placarP1++;
            return true;
        }
        if((this.matriz[0][0] + this.matriz[1][1] + this.matriz[2][2]) == 3){
            this.placarP2++;
            this.placarPc++;
            return true;
        }
        if((this.matriz[0][2] + this.matriz[1][1] + this.matriz[2][0]) == -3){
            this.placarP1++;
            return true;
        }
        if((this.matriz[0][2] + this.matriz[1][1] + this.matriz[2][0]) == 3){
            this.placarP2++;
            this.placarPc++;
            return true;
        }

        return false;

    }

    /**
    * Após o jogador humano marcar sua posição, o programa aguarda
    * um tempo para dar fluência e dinamismo ao jogo
    * */
    public void espera(int tempo) throws InterruptedException {
        sleep(tempo);
    }

    public int getPlacarPc(){
        return placarPc;
    }

    /**
    * @param linha é a linha que a inteligência escolheu para se jogar o marcador
    * */
    public int linhaInteligencia(){
        int linha = posicao / 10;
        return linha;
    }

    /**
    * @param coluna é a coluna que a inteligência escolheu para se jogar o marcador
    * */
    public int colunaInteligencia(){
        int coluna = posicao % 10;
        return coluna;
    }

    /**
    * Na posição escolhida pela inteligência é setado o seu marcador
    * */
    public void pcJoga(int umaLinha, int umaColuna){
        this.matriz[umaLinha][umaColuna] = 1;
        this.vez++;
    }

    /**
    * O jogo se divide em três etapas importates.
    * 1 - Verifica se pode ganhar o jogo neste turno:
    *   Se puder, ele joga nesta possição para ganhar
    *   Se não puder, ele passa próxima etapa
    * 2 - Verifica se o adversário poderá ganhar o jogo
    *   Se puder, ele bloqueia a jogada possível
    *   Se não puder, ele passa para próxima etapa
    * 3 - Continua o plano/estratégia para ganhar o jogo
    * */
    public void estrategia(){

        if (pcGanhaOnde() > 0){
            posicao = pcGanhaOnde();
        }else if (adversarioPodeGanhar() > 0){
            posicao = adversarioPodeGanhar();
        }else{
            posicao = jogadaPadrão();
        }
    }

    /**
    * Verifica se o adversário pode ganhar o jogo no próximo turno
    * @return a posicão se possível ou um valor negativo caso contrário
    **/
    public int adversarioPodeGanhar(){
        int pos;
        int soma;

        //horizontal
        for (int i = 0; i < 3; i++){
            soma = 0;
            pos = 0;
            for (int j = 0; j < 3; j++){
                soma += this.matriz[i][j];
                if(this.matriz[i][j] == 0){
                    pos = i*10 + j;
                }
            }
            if(soma == -2){
                return pos;
            }
        }

        //vertical
        for (int i = 0; i < 3; i++){
            soma = 0;
            pos = 0;
            for (int j = 0; j < 3; j++){
                soma += this.matriz[j][i];
                if(this.matriz[j][i] == 0){
                    pos = j*10 + i;
                }
            }
            if(soma == -2){
                return pos;
            }
        }

        //diagonais
        soma = this.matriz[0][0] + this.matriz[1][1] +this.matriz[2][2];
        if(soma == -2){
            for (int j = 0; j < 3; j++){
                if (this.matriz[j][j] == 0){
                    pos = j*10 + j;
                    return pos;
                }
            }
        }

        soma = this.matriz[0][2] + this.matriz[1][1] +this.matriz[2][0];
        if (soma == -2){
            if (this.matriz[0][2] == 0){
                return 2;
            }else if (this.matriz[1][1] == 0){
                return 11;
            }else if (this.matriz[2][0] == 0){
                return 20;
            }
        }

        return -666;
    }

    /**
    * Verifica se o PC pode ganhar o jogo neste turno
    * @return a posição se possível ou um valor negativo caso contrário
    * */
    public int pcGanhaOnde(){
        int pos;
        int soma;

        //horizontal
        for (int i = 0; i < 3; i++){
            soma = 0;
            pos = 0;
            for (int j = 0; j < 3; j++){
                soma += this.matriz[i][j];
                if(this.matriz[i][j] == 0){
                    pos = i*10 + j;
                }
            }
            if(soma == 2){
                return pos;
            }
        }

        //vertical
        for (int i = 0; i < 3; i++){
            soma = 0;
            pos = 0;
            for (int j = 0; j < 3; j++){
                soma += this.matriz[j][i];
                if(this.matriz[j][i] == 0){
                    pos = j*10 + i;
                }
            }
            if(soma == 2){
                return pos;
            }
        }

        //diagonais
        soma = this.matriz[0][0] + this.matriz[1][1] +this.matriz[2][2];
        if(soma == 2){
            for (int j = 0; j < 3; j++){
                if (this.matriz[j][j] == 0){
                    pos = j*10 + j;
                    return pos;
                }
            }
        }

        soma = this.matriz[0][2] + this.matriz[1][1] +this.matriz[2][0];
        if (soma == 2){
            if (this.matriz[0][2] == 0){
                return 2;
            }else if (this.matriz[1][1] == 0){
                return 11;
            }else if (this.matriz[2][0] == 0){
                return 20;
            }
        }

        return -666;

    }

    /**
    * Verifica se há algum dos quatro cantos disponível sendo que sua
    * linha e sua coluna também deve estar toda vazia. Por exemplo:
    * se a linha 0 e coluna 0 estiverem toda vazia, então é escolhido
    * a posição 00 como o "melhor canto" para se jogar
    *
    * @return a posição ou um número negativo para informar que não há um canto
    * */
    public int melhorCanto() {
        if (this.matriz[0][0] == 0) {
            if (this.matriz[0][1] == 0 && this.matriz[0][2] == 0 && this.matriz[1][0] == 0 && this.matriz[1][2] == 0) {
                return 0;
            }
        }
        if (this.matriz[0][2] == 0) {
            if (this.matriz[0][0] == 0 && this.matriz[0][1] == 0 && this.matriz[1][2] == 0 && this.matriz[2][2] == 0) {
                return 2;
            }
        }
        if (this.matriz[2][0] == 0) {
            if (this.matriz[0][0] == 0 && this.matriz[0][1] == 0 && this.matriz[2][1] == 0 && this.matriz[2][2] == 0) {
                return 20;
            }
        }
        if (this.matriz[2][2] == 0) {
            if (this.matriz[0][2] == 0 && this.matriz[1][2] == 0 && this.matriz[2][1] == 0 && this.matriz[2][0] == 0) {
                return 22;
            }
        }

        return -666;
    }

    /**
    * Sendo que em algum dos cantos há um marcador, ele verifica qual outro
    * canto poderia fazer um par com ele com a posição entre eles livre.
    * @return canto com visada direta para algum marcador ou retorna número negativo como negação
    * */
    public int segundoMelhorCanto(){
        int pos;

        if(this.matriz[0][0] == 1 && this.matriz[0][1] == 0 && this.matriz[0][2] == 0) pos = 2;
        else if(this.matriz[0][0] == 1 && this.matriz[1][0] == 0 && this.matriz[2][0] == 0) pos = 20;

        else if(this.matriz[0][2] == 1 && this.matriz[0][1] == 0 && this.matriz[0][0] == 0) pos = 0;
        else if(this.matriz[0][2] == 1 && this.matriz[1][2] == 0 && this.matriz[2][2] == 0) pos = 22;

        else if(this.matriz[2][0] == 1 && this.matriz[2][2] == 0 && this.matriz[2][1] == 0) pos = 22;
        else if(this.matriz[2][0] == 1 && this.matriz[1][0] == 0 && this.matriz[0][0] == 0) pos = 0;

        else if(this.matriz[2][2] == 1 && this.matriz[1][2] == 0 && this.matriz[0][2] == 0) pos = 2;
        else if(this.matriz[2][2] == 1 && this.matriz[2][1] == 0 && this.matriz[2][0] == 0) pos = 20;
        else pos = -666;

        return pos;
    }

    /**
    * @return a primeira posição vazia no tabuleiro
    * */
    public int primeiroVazio(){
        int pos;

        if(this.matriz[0][0] == 0) pos = 0;
        else if(this.matriz[0][1] == 0) pos = 1;
        else if(this.matriz[0][2] == 0) pos = 2;
        else if(this.matriz[1][0] == 0) pos = 10;
        else if(this.matriz[1][1] == 0) pos = 11;
        else if(this.matriz[1][2] == 0) pos = 12;
        else if(this.matriz[2][0] == 0) pos = 20;
        else if(this.matriz[2][1] == 0) pos = 21;
        else pos = 22;

        return pos;
    }

    /**
    * @return o canto em que o adversário marcou ou número negativo como negação
    * */
    public int adversarioJogouCanto(){

        if(this.matriz[0][0] == -1){
            return 0;
        }else if(this.matriz[0][2] == -1){
            return 2;
        }else if(this.matriz[2][0] == -1){
            return 20;
        }else if(this.matriz[2][2] == -1){
            return 22;
        }

        return -666;
    }

    /**
    * @return posição com possibilidade para ganhar naquela linha ou coluna
    * */
    public int linhaOuColunaPraGanhar(){

        //linha
        if((matriz[0][0] + matriz[0][1] + matriz[0][2]) == 1 ){
            if (matriz[0][0] == 0) return 0;
            else if (matriz[0][1] == 0) return 1;
            else if (matriz[0][2] == 0) return 02;
        }
        if((matriz[1][0] + matriz[1][1] + matriz[1][2]) == 1){
            if (matriz[1][0] == 0) return 10;
            else if (matriz[1][1] == 0) return 11;
            else if (matriz[1][2] == 0) return 12;
        }
        if((matriz[2][0] + matriz[2][1] + matriz[2][2]) == 1){
            if (matriz[2][0] == 0) return 20;
            else if (matriz[2][1] == 0) return 21;
            else if (matriz[2][2] == 0) return 22;
        }

        //coluna
        if((matriz[0][0] + matriz[1][0] + matriz[2][0]) == 1){
            if (matriz[0][0] == 0) return 0;
            else if (matriz[1][0] == 0) return 10;
            else if (matriz[2][0] == 0) return 20;
        }
        if((matriz[0][1] + matriz[1][1] + matriz[2][1]) == 1){
            if (matriz[0][1] == 0) return 1;
            else if (matriz[1][1] == 0) return 11;
            else if (matriz[2][1] == 0) return 21;
        }
        if((matriz[0][2] + matriz[1][2] + matriz[2][2]) == 1){
            if (matriz[0][2] == 0) return 2;
            else if (matriz[1][2] == 0) return 12;
            else if (matriz[2][2] == 0) return 22;
        }

        //diagonal
        if((matriz[0][0] + matriz[1][1] + matriz[2][2]) == 1){
            if (matriz[0][0] == 0) return 0;
            else if (matriz[1][1] == 0) return 11;
            else if (matriz[2][2] == 0) return 22;
        }
        if((matriz[0][2] + matriz[1][1] + matriz[2][0]) == 1){
            if (matriz[0][2] == 0) return 2;
            else if (matriz[1][1] == 0) return 11;
            else if (matriz[2][0] == 0) return 20;
        }

        return -666;

    }

    /**
    * Quando não se pode ganhar e nem perder o jogo, este é o plano que se deve seguir para ganhar/nãoPerder
    *
    * Jogadores experientes possuem uma estratégia, a IA do jogo imita essa estrátégia.
    * Ela se resume em tentar realizar um triângulo para armar uma armadilha para o adversário.
    * Exemplo:
    * X |   | X        |   | X      X |   |        X |   | X
    *   |   |    ou    |   |    ou    |   |    ou    |   |
    * X |   |        X |   | X      X |   | X        |   | X
    * @return a posição escolhida pela inteligência
    * */
    public int jogadaPadrão(){

        if (segundoMelhorCanto() >= 0){
            return segundoMelhorCanto();
        }else if(adversarioJogouCanto() >= 0 && this.matriz[1][1] == 0){
            return 11;
        }else if (segundoMelhorCanto() >= 0){
            return segundoMelhorCanto();
        }else if(melhorCanto() >= 0){
            return melhorCanto();
        }else if(linhaOuColunaPraGanhar() >= 0){
            return linhaOuColunaPraGanhar();
        }else {
            return primeiroVazio();
        }
    }


    public static void limparTelaTerminalVT100(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}

