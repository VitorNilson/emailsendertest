package com.example.demo.service;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.retConsSitNFe.TRetConsSitNFe;
import br.com.swconsultoria.nfe.util.NFCeUtil;
import br.com.swconsultoria.nfe.wsdl.NFeConsultaProtocoloMS.NFeConsultaProtocolo4Stub;
import com.example.demo.mod.SendMailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import scala.math.ScalaNumber;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.util.Scanner;

@Component
public class EmailService{

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(
            SendMailDTO sendMailDTO) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(sendMailDTO.getTo());
        message.setText(sendMailDTO.getText());

        emailSender.send(message);

    }

    public String consultaNFce() throws CertificadoException, NfeException, IOException {

        StringBuilder content = new StringBuilder();
        URLConnection connection = null;
        try{
            connection = new URL("http://nfe.sefaz.go.gov.br/nfeweb/sites/nfce/danfeNFCe?p" +
                    "=52191212625512000122650010000902691000000011%7C2%7C1%7C1%7C8C7C1D53EBA293BC06248A420EE55162C2C14C3B").openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            while(scanner.hasNext())
                content.append(scanner.next());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return content.toString();
    }


}
