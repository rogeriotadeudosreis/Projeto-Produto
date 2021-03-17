/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author roger
 */
public class ServidorComunicador {

    private ServerSocket serversocket;

    public void criarServerSocket(int porta) throws IOException {
        serversocket = new ServerSocket(porta);
    }

    public Socket esperarConexao() throws IOException {
        Socket socket = serversocket.accept();
        return socket;
    }

    //protocolo da aplicação
    //....cliente......SOCKET.....servidor.....
    public void trataConexao(Socket socket) throws IOException {

        try {
            //Criado stream de entrada e saida de mensagens entre cliente e servidor
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            String msg = input.readUTF();
            System.out.println("Mensagem recebida..." + msg);
            output.writeUTF("Valeu");

            //Fecha streams de entrada e saida de mensagens
            input.close();
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorComunicador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //Fecha a conexão do servidor/cliente
            fecharConexao(socket);
        }
    }

    public void fecharConexao(Socket socket) throws IOException {
        socket.close();
    }

}
