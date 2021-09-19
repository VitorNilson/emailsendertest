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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

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

    public TRetConsSitNFe consultaNFce() throws CertificadoException, NfeException {
        Certificado certificado = new Certificado();
        String chave = "35210808385169000119590001294641606301830190";
        ConfiguracoesNfe configuracoesNfe = ConfiguracoesNfe.criarConfiguracoes(EstadosEnum.SP, AmbienteEnum.PRODUCAO, certificado, "NFCE" );
        TRetConsSitNFe retorno = Nfe.consultaXml(configuracoesNfe, chave, DocumentoEnum.NFCE);
        return retorno;
    }


}
