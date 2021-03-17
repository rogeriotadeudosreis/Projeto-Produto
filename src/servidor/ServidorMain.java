/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author roger
 */
public class ServidorMain {

    public static void main(String[] args) throws IOException {
        try {
            ServidorComunicador servidor = new ServidorComunicador();
            System.out.println("Aguardando conexão...");
            servidor.criarServerSocket(6789);
            Socket socket = servidor.esperarConexao();//protocolo
            System.out.println("Cliente conectado.");
            servidor.trataConexao(socket);

        } catch (IOException erro) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro na comunicação.\n" + erro);
        }

    }

}
