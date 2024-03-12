package XMLCreation;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class TestCRed {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        //Element rootelement = doc.createElement("Document");
        String ppp = "urn:swift:saa:xsd:saa.2.0";
        //String qqq = http://www.w3.org/2001/XMLSchema-instance;
        //String bbb = "urn:iso:std:iso:20022:tech:xsd:pacs.002.001.10 file:///D:/workspaceSPAG/IPSProject/InstantAXE/xsd/pacs.002.001.10.xsd";
        //rootelement.setAttribute("xmlns:xsi", qqq);
        //rootelement.setAttribute("xmlns", ppp);


        //rootelement.setAttribute("xsi:schemaLocation", bbb);
        //doc.appendChild(rootelement);
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 20;
        Random random = new Random();

        String TempMsgIdText = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        
        System.out.println(TempMsgIdText);
        //Creating timestamp
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat aaa = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat ccc = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat ddd = new SimpleDateFormat("HHmmss");
        SimpleDateFormat eee = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = sdf.format(date);
        String OnlyDate = aaa.format(date);
        String currdate = formattedDate.replace(" ", "T");
        String Messagetime1 =ccc.format(date);
        String Messagetime2 =ddd.format(date);
        String Messagetime3 = eee.format(date);
        String Messagetime =Messagetime1+"T"+Messagetime2;
        String Messagetime12 =currdate+"T"+Messagetime3;
        String timestamp = Messagetime1+Messagetime2;
        System.out.println(currdate);
        System.out.println(OnlyDate);
        System.out.println(Messagetime);
        String MsgIdText ="INNDNL2U"+Messagetime ;
        System.out.println(MsgIdText);
        // Create root element with namespaces

        Element rootElement = doc.createElementNS("urn:swift:saa:xsd:saa.2.0", "Saa:DataPDU");
        rootElement.setAttribute("xmlns:Sw", "urn:swift:snl:ns.Sw");
       
        rootElement.setAttribute("xmlns:SwGbl", "urn:swift:snl:ns.SwGbl");
        rootElement.setAttribute("xmlns:SwInt", "urn:swift:snl:ns.SwInt");
        rootElement.setAttribute("xmlns:SwSec", "urn:swift:snl:ns.SwSec");
        doc.appendChild(rootElement);
        
        //Saa:Revision
        Element Rev = doc.createElement("Saa:Revision");
        rootElement.appendChild(Rev);
        Rev.setTextContent("2.0.8");

        // Create FIToFIPmtStsRpt element
        Element Header = doc.createElement("Saa:Header");
        rootElement.appendChild(Header);

        // Create GrpHdr element
        Element Message = doc.createElement("Saa:Message");
        Header.appendChild(Message);

        // Add elements to Header
        appendChildWithTextContent(doc, Message, "Saa:SenderReference", "ONNBANL2GXXXPACS2023080407");
        appendChildWithTextContent(doc, Message, "Saa:MessageIdentifier", "pacs.008.001.08");
        appendChildWithTextContent(doc, Message, "Saa:Format", "MX");
        appendChildWithTextContent(doc, Message, "Saa:SubFormat", "Output");

        // Create Saa:Sender element
        Element Sender = doc.createElement("Saa:Sender");
        Message.appendChild(Sender);
        
        
        appendChildWithTextContent(doc, Sender, "Saa:DN", "cn=rtgs,o=trgtxepm,o=swift");
        Element FullName = doc.createElement("Saa:FullName");
        Sender.appendChild(FullName);
        appendChildWithTextContent(doc, FullName, "Saa:X1", "TRGTXEPMXXX");
        appendChildWithTextContent(doc, FullName, "Saa:X2", "rtgs");
        
        Element Receiver = doc.createElement("Saa:Receiver");
        Message.appendChild(Receiver);
        
        
        appendChildWithTextContent(doc, Receiver, "Saa:DN", "o=NNBANL2G,o=swift");
        Element FullName1 = doc.createElement("Saa:FullName");
        Receiver.appendChild(FullName1);
        appendChildWithTextContent(doc, FullName1, "Saa:X1", "NNBANL2GXXX");
        
        //InterfaceInfo
        
        Element InterfaceInfo = doc.createElement("Saa:InterfaceInfo");
        Message.appendChild(InterfaceInfo);
        
        
        appendChildWithTextContent(doc, InterfaceInfo, "Saa:MessageCreator", "SWIFTNetInterface");
        appendChildWithTextContent(doc, InterfaceInfo, "Saa:MessageContext", "Original");
        appendChildWithTextContent(doc, InterfaceInfo, "Saa:MessageNature", "Financial");
        
        
        //Saa:NetworkInfo
        
        Element NetworkInfo = doc.createElement("Saa:NetworkInfo");
        Message.appendChild(NetworkInfo);
        
        
        appendChildWithTextContent(doc, NetworkInfo, "Saa:Priority", "Urgent");
        appendChildWithTextContent(doc, NetworkInfo, "Saa:IsPossibleDuplicate", "false");
        appendChildWithTextContent(doc, NetworkInfo, "Saa:Service", "esmig.t2.iast");
        appendChildWithTextContent(doc, NetworkInfo, "Saa:Network", "SWIFTNet");
        appendChildWithTextContent(doc, NetworkInfo, "Saa:SessionNr", "000000");
        appendChildWithTextContent(doc, NetworkInfo, "Saa:SeqNr", "000000000");
        
        //Saa:SWIFTNetNetworkInfo
        
        Element SWIFTNetNetworkInfo = doc.createElement("Saa:SWIFTNetNetworkInfo");
        NetworkInfo.appendChild(SWIFTNetNetworkInfo);
        
        
        appendChildWithTextContent(doc, SWIFTNetNetworkInfo, "Saa:RequestType", "pacs.008.001.08");
        appendChildWithTextContent(doc, SWIFTNetNetworkInfo, "Saa:SWIFTRef", "swi01247-2023-08-04T07:43:25.09249.088601Z");
        appendChildWithTextContent(doc, SWIFTNetNetworkInfo, "Saa:SNLRef", "snp01244-2023-08-04T07:43:25.09249.088601Z");
        appendChildWithTextContent(doc, SWIFTNetNetworkInfo, "Saa:Reference", "REFERENCE_FROM_SAA");
        appendChildWithTextContent(doc, SWIFTNetNetworkInfo, "Saa:SnFInputTime", "0301:2022-09-19T07:43:25");
        appendChildWithTextContent(doc, SWIFTNetNetworkInfo, "Saa:SnFDeliveryTime", "2023-08-04T07:43:29Z");
        appendChildWithTextContent(doc, SWIFTNetNetworkInfo, "Saa:CreationTime", "");
        
        
        //Saa:SecurityInfo
        Element SecurityInfo = doc.createElement("Saa:SecurityInfo");
        Message.appendChild(SecurityInfo);
        
        //Saa:SWIFTNetSecurityInfo
        Element SWIFTNetSecurityInfo = doc.createElement("Saa:SWIFTNetSecurityInfo");
        SecurityInfo.appendChild(SWIFTNetSecurityInfo);
        
        
        appendChildWithTextContent(doc, SWIFTNetSecurityInfo, "Saa:SignatureResult", "Success");
        appendChildWithTextContent(doc, SWIFTNetSecurityInfo, "Saa:SignatureValue", "");
        
        
        //Saa:Body
        Element Body = doc.createElement("Saa:Body");
        rootElement.appendChild(Body);
        Element AppHdr = doc.createElement("AppHdr");
        Body.appendChild(AppHdr);
        
        
       
        AppHdr.setAttribute("xmlns", "urn:iso:std:iso:20022:tech:xsd:head.001.001.01");
        AppHdr.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        
        Element Fr = doc.createElement("Fr");
        AppHdr.appendChild(Fr);
        
        Element FIId = doc.createElement("FIId");
        Fr.appendChild(FIId);
        
        Element FinInstnId = doc.createElement("FinInstnId");
        FIId.appendChild(FinInstnId);

        appendChildWithTextContent(doc, FinInstnId, "BICFI","ABNANL2AXXX");
        
        Element To = doc.createElement("To");
        AppHdr.appendChild(To);
        
        Element FIId1 = doc.createElement("FIId");
        To.appendChild(FIId1);
        
        Element FinInstnId1 = doc.createElement("FinInstnId");
        FIId1.appendChild(FinInstnId1);

        appendChildWithTextContent(doc, FinInstnId1, "BICFI","NNBANL2GXXX");
        
        appendChildWithTextContent(doc, AppHdr, "BizMsgIdr","6437501");
        appendChildWithTextContent(doc, AppHdr, "MsgDefIdr","pacs.008.001.08");
        appendChildWithTextContent(doc, AppHdr, "CreDt","2023-08-04T07:43:25.531Z");
        
        //Sgntr
        Element Sgntr = doc.createElement("Sgntr");
        AppHdr.appendChild(Sgntr);
        Element Signature = doc.createElement("ds:Signature");
        Sgntr.appendChild(Signature);
        Signature.setAttribute("xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
        Signature.setAttribute("Id", "_827c1e85-30b2-495e-beba-0e928f02c5ef");
        
        //SignedInfo
        Element SignedInfo = doc.createElement("ds:SignedInfo");
        Signature.appendChild(SignedInfo);
        Element CanonicalizationMethod = doc.createElement("ds:CanonicalizationMethod");
        SignedInfo.appendChild(CanonicalizationMethod);
        CanonicalizationMethod.setAttribute("Algorithm", "http://www.w3.org/2001/10/xml-exc-c14n#");
        
        Element SignatureMethod = doc.createElement("ds:SignatureMethod");
        SignedInfo.appendChild(SignatureMethod);
        SignatureMethod.setAttribute("Algorithm", "http://www.w3.org/2001/04/xmldsig-more#rsa-sha256");
        
        //Reference
        Element Reference = doc.createElement("ds:Reference");
        SignedInfo.appendChild(Reference);
        Reference.setAttribute("URI", "#_f3f9f586-d18a-49a4-bfe3-0ba446fb6f6c");
        
        //Transforms
        Element Transforms = doc.createElement("ds:Transforms");
        Reference.appendChild(Transforms);
        Element Transforms1 = doc.createElement("ds:Transform");
        Transforms.appendChild(Transforms1);
        Transforms1.setAttribute("Algorithm", "http://www.w3.org/2001/10/xml-exc-c14n#");
        
        //<ds:DigestMethod Algorithm="http://www.w3.org/2001/04/xmlenc#sha256"/>
        //<ds:DigestValue>iQ65iJPgT7OGjvZF1nVFrc13ezbeUn0RoUgylxXDTMM=</ds:DigestValue>
        
        Element DigestMethod = doc.createElement("ds:DigestMethod");
        Reference.appendChild(DigestMethod);
        DigestMethod.setAttribute("Algorithm", "http://www.w3.org/2001/04/xmlenc#sha256");
        
        Element DigestValue = doc.createElement("ds:DigestValue");
        Reference.appendChild(DigestValue);
        DigestValue.setTextContent("iQ65iJPgT7OGjvZF1nVFrc13ezbeUn0RoUgylxXDTMM=");
        
        //Reference
        Element Reference1 = doc.createElement("ds:Reference");
        SignedInfo.appendChild(Reference1);
       // Reference1.setAttribute("URI", "");
        
        //Transforms
        Element Transforms111 = doc.createElement("ds:Transforms");
        Reference1.appendChild(Transforms111);
        Reference1.setAttribute("URI", "");
        Element Transforms11 = doc.createElement("ds:Transform");
        Transforms111.appendChild(Transforms11);
        Transforms11.setAttribute("Algorithm", "http://www.w3.org/2000/09/xmldsig#enveloped-signature");
        Element Transforms112 = doc.createElement("ds:Transform");
        Transforms111.appendChild(Transforms112);
        Transforms112.setAttribute("Algorithm", "http://www.w3.org/2001/10/xml-exc-c14n#");
        //<ds:DigestMethod Algorithm="http://www.w3.org/2001/04/xmlenc#sha256"/>
        //<ds:DigestValue>iQ65iJPgT7OGjvZF1nVFrc13ezbeUn0RoUgylxXDTMM=</ds:DigestValue>
        
        Element DigestMethod1 = doc.createElement("ds:DigestMethod");
        Reference1.appendChild(DigestMethod1);
        DigestMethod1.setAttribute("Algorithm", "http://www.w3.org/2001/04/xmlenc#sha256");
        
        Element DigestValue1 = doc.createElement("ds:DigestValue");
        Reference1.appendChild(DigestValue1);
        DigestValue1.setTextContent("6WBpyjtMJ7/HQI8th/sA9W0Nb9qCHNEsLG3rKDYIhU4=");
        
        
        //Reference
        Element Reference2 = doc.createElement("ds:Reference");
        SignedInfo.appendChild(Reference2);
       // Reference2.setAttribute("URI", " ");
        
        //Transforms
        Element Transforms2 = doc.createElement("ds:Transforms");
        Reference2.appendChild(Transforms2);
        Element Transforms3 = doc.createElement("ds:Transform");
        Transforms2.appendChild(Transforms3);
        Transforms3.setAttribute("Algorithm", "http://www.w3.org/2001/10/xml-exc-c14n#");
       
        
        Element DigestMethod2 = doc.createElement("ds:DigestMethod");
        Reference2.appendChild(DigestMethod2);
        DigestMethod2.setAttribute("Algorithm", "http://www.w3.org/2001/04/xmlenc#sha256");
        
        Element DigestValue2 = doc.createElement("ds:DigestValue");
        Reference2.appendChild(DigestValue2);
        DigestValue2.setTextContent("clqM6ON6ZsIH3UQY5xZdQ9uBajI7NoYEMwU+X4+w5og=");
        
        //ds:SignatureValue
        Element SignatureValue = doc.createElement("ds:SignatureValue");
        Signature.appendChild(SignatureValue);
        SignatureValue.setTextContent("aTq0NYNXCZ/bqYyYVeEtCCLwzpI6uYb+BtG3m/QKXuEJbbWB4BBtRevSYWM/rMuFJLfjlcbTm/qsp5o+h62KSmS5QcJk4qjd9lc0QGle2zCciYGQluz8qNQfG0tm4vb1M6NSUD1WzZlBmW3BRIEynGxPgd58V3P6lJoBrGBmINOBNil7ZIdNcCRshycNgYfevxxiwgsuAYnNCPwTGFYx5pcQkssI4vAaVocnQkd59Gpcan69/B9aKo1SeMIvSO0ViehjUFP6u77evUzgAIf+j2h/5orNDehb1gYVD/Ysitid1Sh1WfLjH/QhaTz8TFhb72GcQDXnKD25T/ckR074oQ==");
        
        //ds:KeyInfo
        Element KeyInfo = doc.createElement("ds:KeyInfo");
        Signature.appendChild(KeyInfo);
        KeyInfo.setAttribute("Id", "_f3f9f586-d18a-49a4-bfe3-0ba446fb6f6c");
        
        //ds:X509Data
        Element X509Data = doc.createElement("ds:X509Data");
        KeyInfo.appendChild(X509Data);
        Element X509Certificate = doc.createElement("ds:X509Certificate");
        X509Data.appendChild(X509Certificate);
        X509Certificate.setTextContent("MIIExTCCAq2gAwIBAgIEYPzaKzANBgkqhkiG9w0BAQsFADAQMQ4wDAYDVQQKEwVTV0lGVDAeFw0yMTA4MzAxNDM1MjlaFw0yMzA4MzAxNTA1MjlaMEYxDjAMBgNVBAoTBXN3aWZ0MREwDwYDVQQKEwh0cmd0eGVwbTEOMAwGA1UECxMFdXRlc3QxETAPBgNVBAMTCHN3aWZ0LXQyMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAitVMF+IYKja5tB3pJW9MpFhkJibmchd/5u6miMIkdSNrzCXiWtDHuNGjbkU9C68X1qEWqcrU5RvcJNDCQsokTleMeoAUwv/aFVBXXoGZOJ57mz8I3szPdWgE7Fvtf+pcriscDGCPbsORvESWsgCVwxTs21LOsFbOTolx6IAZb8pEBAlMWYUhUaTCLh0s0NDn45X2HTAkwlTrLBljYQTNrpV1axkSwV4qu67t0dmNXHLFqJ4U3auuG0BWLPQv5MJaRFLZIddIGaereqehDf1tByk75dmltN6F9VBgWvdTpFoXN2jIZA1qdjpfDtTUhaDO6BW7e93Yh5EXzIc8Y2S0bwIDAQABo4HwMIHtMAsGA1UdDwQEAwIE8DAUBgNVHSAEDTALMAkGBysVBgMKZAIwNQYDVR0fBC4wLDAqoCigJqQkMCIxDjAMBgNVBAoTBVNXSUZUMRAwDgYDVQQDEwdDUkw0NDAwMCsGA1UdEAQkMCKADzIwMjEwODMwMTQzNTI5WoEPMjAyMzA4MzAxNTA1MjlaMB8GA1UdIwQYMBaAFFKjAShg2ZUnQeIQngmfq4PQ8ZZCMB0GA1UdDgQWBBSfDx0nsm2sj56hopBKml1yQZjl5DAJBgNVHRMEAjAAMBkGCSqGSIb2fQdBAAQMMAobBFY4LjIDAgOoMA0GCSqGSIb3DQEBCwUAA4ICAQDFQURrTaWtHrTTSkZT7qWIToHBGPcJiD7AbxSg/7ZvTBTjfAALEaONBGD28S+wVNP6PKYHFe/JI05vxlEJnz1VYn4mtNdvg+GJ9jdNTs+o3+z0UtY7dpjoNBXh+UNknM6vFsJuOrqy0xXb9XVzqfIv/mtcQ7y8v5F10T5DINcXAqw7valKSW5VCDSxqDBhgMuR6dVpFbz31dagHGdXkwnp0utcb4mgezivm1Ml8DEw+4QQQoC35ave3P5S8eMbyEXTmEAn+YPYLiqHfggRBfwy02anTh7WK4tegMhgbIy142yR4ozSVHyi6otgLtGVflOJVmWPFeotRQ+BLAFXQONP95/5hyQJyaTpLUHbdrtAWNjUNctaOQldLe80hBTbzVdTLALlyq9dV7AovmQukj6Fam95gnyUWMVFhE2Wa+6FzZvwxOZ5HRh4qO10d7a7XwyIn9xBaPGYQ83PTOYhMZNBJPjndsANu4xq9LbRjlpf+760/IZivPEqn9PCfk9o8pebfbAHlxUzBdAfBz3oV8mH6dfVEjV0ZMjx9hMSIWV/EiW2t7WJyyatN9qytITkwWhydDjDo339Txsjxget3VGZJ4yOHYHyin3JhW1neysnsQoFG1le+ahSrLsI8MbWbMpsiwYSvsrGgQqSNQ51YLYhhbbQZUdnze76x8BjlJ9Q6Q==");
        
        //Document
        Element Document = doc.createElement("Document");
        Body.appendChild(Document);
        
        
       
        Document.setAttribute("xmlns", "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.08");
        Document.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");

        // Create FIToFICstmrCdtTrf element
        Element FIToFICstmrCdtTrf = doc.createElement("FIToFICstmrCdtTrf");
        Document.appendChild(FIToFICstmrCdtTrf);
        
        
        Element GrpHdr = doc.createElement("GrpHdr");
        FIToFICstmrCdtTrf.appendChild(GrpHdr);
        
        appendChildWithTextContent(doc, GrpHdr, "MsgId", "EPPCH35");
        appendChildWithTextContent(doc, GrpHdr, "CreDtTm", "2023-08-04T07:43:25.531+00:00");
        appendChildWithTextContent(doc, GrpHdr, "NbOfTxs", "1");
        
        Element SttlmInf1 = doc.createElement("SttlmInf");
        GrpHdr.appendChild(SttlmInf1);
        Element SttlmMtd = doc.createElement("SttlmMtd");
        SttlmInf1.appendChild(SttlmMtd);
        SttlmMtd.setTextContent("CLRG");
        
        Element ClrSys = doc.createElement("ClrSys");
        SttlmInf1.appendChild(ClrSys);
        Element Cd = doc.createElement("Cd");
        ClrSys.appendChild(Cd);
        Cd.setTextContent("TGT");
        
       
        // Add elements to TxInfAndSts
//        appendChildWithTextContent(doc, TxInfAndSts, "StsId", "RtrId20230626023814INNDNL2U90874395");
//        appendChildWithTextContent(doc, TxInfAndSts, "OrgnlInstrId", "NOTPROVIDED");
//        appendChildWithTextContent(doc, TxInfAndSts, "OrgnlEndToEndId", "3981664");
//        appendChildWithTextContent(doc, TxInfAndSts, "OrgnlTxId", "BNK23052000CGL0K");
//        appendChildWithTextContent(doc, TxInfAndSts, "TxSts", "RJCT");

        // Create StsRsnInf element
        Element StsRsnInf = doc.createElement("StsRsnInf");
      //  TxInfAndSts.appendChild(StsRsnInf);

        // Add Orgtr element to StsRsnInf
        Element Orgtr = doc.createElement("Orgtr");
        StsRsnInf.appendChild(Orgtr);

        // Add Id element to Orgtr
        Element Id = doc.createElement("Id");
        Orgtr.appendChild(Id);

        // Add OrgId element to Id
        Element OrgId = doc.createElement("OrgId");
        Id.appendChild(OrgId);

        // Add AnyBIC element to OrgId
        Element AnyBIC = doc.createElement("AnyBIC");
        OrgId.appendChild(AnyBIC);
        AnyBIC.setTextContent("INNDNL2U");

        // Add Rsn element to StsRsnInf
        Element Rsn = doc.createElement("Rsn");
        StsRsnInf.appendChild(Rsn);

        // Add Cd element to Rsn
        appendChildWithTextContent(doc, Rsn, "Cd", "AM04");

        // Create OrgnlTxRef element
        Element OrgnlTxRef = doc.createElement("OrgnlTxRef");
    //    TxInfAndSts.appendChild(OrgnlTxRef);
//
        // Add elements to OrgnlTxRef
        appendChildWithTextContent(doc, OrgnlTxRef, "IntrBkSttlmAmt", "31.00", "Ccy", "EUR");
        appendChildWithTextContent(doc, OrgnlTxRef, "IntrBkSttlmDt", "2023-06-27");
        appendChildWithTextContent(doc, OrgnlTxRef, "ReqdColltnDt", "2023-02-23");

        // Create CdtrSchmeId element
        Element CdtrSchmeId = doc.createElement("CdtrSchmeId");
        OrgnlTxRef.appendChild(CdtrSchmeId);

        // Add Id element to CdtrSchmeId
        Element Id2 = doc.createElement("Id");
        CdtrSchmeId.appendChild(Id2);

        // Add PrvtId element to Id2
        Element PrvtId = doc.createElement("PrvtId");
        Id2.appendChild(PrvtId);

        // Add Othr element to PrvtId
        Element Othr = doc.createElement("Othr");
        PrvtId.appendChild(Othr);

        // Add Id element to Othr
        appendChildWithTextContent(doc, Othr, "Id", "NL47ZZZ524034240000");

        // Add SchmeNm element to Othr
        Element SchmeNm = doc.createElement("SchmeNm");
        Othr.appendChild(SchmeNm);

        // Add Prtry element to SchmeNm
        appendChildWithTextContent(doc, SchmeNm, "Prtry", "SEPA");

//        // Create SttlmInf element
//      //  Element SttlmInf = doc.createElement("SttlmInf");
//    //    OrgnlTxRef.appendChild(SttlmInf);
//
//        // Add elements to SttlmInf
//        appendChildWithTextContent(doc, SttlmInf, "SttlmMtd", "CLRG");
//     ////   Element ClrSys = doc.createElement("ClrSys");
//        SttlmInf.appendChild(ClrSys);
//    //    Element Cd = doc.createElement("Cd");
//        ClrSys.appendChild(Cd);
//        Element INC = doc.createElement("INC");
//        Cd.appendChild(INC);

        // Create PmtTpInf element
        Element PmtTpInf = doc.createElement("PmtTpInf");
        OrgnlTxRef.appendChild(PmtTpInf);

        // Add elements to PmtTpInf
        Element SvcLvl = doc.createElement("SvcLvl");
        PmtTpInf.appendChild(SvcLvl);
        appendChildWithTextContent(doc,SvcLvl , "Cd", "SEPA");
        Element LclInstrm = doc.createElement("LclInstrm");
        PmtTpInf.appendChild(LclInstrm);
        appendChildWithTextContent(doc, LclInstrm, "Cd", "CORE");
        appendChildWithTextContent(doc, PmtTpInf, "SeqTp", "RCUR");

        // Create MndtRltdInf element
        Element MndtRltdInf = doc.createElement("MndtRltdInf");
        OrgnlTxRef.appendChild(MndtRltdInf);

        // Add elements to MndtRltdInf
        appendChildWithTextContent(doc, MndtRltdInf, "MndtId", "41010");
        appendChildWithTextContent(doc, MndtRltdInf, "DtOfSgntr", "2015-09-20");

        // Create RmtInf element
        Element RmtInf = doc.createElement("RmtInf");
        OrgnlTxRef.appendChild(RmtInf);

        // Add Ustrd element to RmtInf
        appendChildWithTextContent(doc, RmtInf, "Ustrd", "Qucpaaf pukeg KIA");

        // Create Dbtr element
        Element Dbtr = doc.createElement("Dbtr");
        OrgnlTxRef.appendChild(Dbtr);

        // Add Pty element to Dbtr
        Element Pty = doc.createElement("Pty");
        Dbtr.appendChild(Pty);

        // Add Nm element to Pty
        appendChildWithTextContent(doc, Pty, "Nm", "T. Vsnmsno s/n Q.A.Q. Awmmzwzo");

        // Create DbtrAcct element
        Element DbtrAcct = doc.createElement("DbtrAcct");
        OrgnlTxRef.appendChild(DbtrAcct);

        // Add Id element to DbtrAcct
        Element Id3 = doc.createElement("Id");
        DbtrAcct.appendChild(Id3);

        // Add IBAN element to Id3
        appendChildWithTextContent(doc, Id3, "IBAN", "NL12RABO0100001213");

        // Create DbtrAgt element
        Element DbtrAgt = doc.createElement("DbtrAgt");
        OrgnlTxRef.appendChild(DbtrAgt);

        // Add FinInstnId element to DbtrAgt
        Element FinInstnId2 = doc.createElement("FinInstnId");
        DbtrAgt.appendChild(FinInstnId2);

        // Add BICFI element to FinInstnId2
        appendChildWithTextContent(doc, FinInstnId2, "BICFI", "RABONL2U");

        // Create CdtrAgt element
        Element CdtrAgt = doc.createElement("CdtrAgt");
        OrgnlTxRef.appendChild(CdtrAgt);

        // Add FinInstnId element to CdtrAgt
        Element FinInstnId3 = doc.createElement("FinInstnId");
        CdtrAgt.appendChild(FinInstnId3);

        // Add BICFI element to FinInstnId3
        appendChildWithTextContent(doc, FinInstnId3, "BICFI", "NNBANL2G");

        // Create Cdtr element
        Element Cdtr = doc.createElement("Cdtr");
        OrgnlTxRef.appendChild(Cdtr);

        // Add Pty element to Cdtr
        Element Pty2 = doc.createElement("Pty");
        Cdtr.appendChild(Pty2);

        // Add Nm element to Pty2
        appendChildWithTextContent(doc, Pty2, "Nm", "Pxcyoixxs Psusvxxiusi Zxih");

        // Add PstlAdr element to Pty2
        Element PstlAdr = doc.createElement("PstlAdr");
        Pty2.appendChild(PstlAdr);

        // Add Ctry element to PstlAdr
        appendChildWithTextContent(doc, PstlAdr, "Ctry", "NL");

        // Add AdrLine elements to PstlAdr
        appendChildWithTextContent(doc, PstlAdr, "AdrLine", "Bxdgtyt Cygixdbrggg 35 3505 ZW 't-Yxguygmgpy");
        appendChildWithTextContent(doc, PstlAdr, "AdrLine", "Netherland street");

        // Create CdtrAcct element
        Element CdtrAcct = doc.createElement("CdtrAcct");
        OrgnlTxRef.appendChild(CdtrAcct);

        // Add Id element to CdtrAcct
        Element Id4 = doc.createElement("Id");
        CdtrAcct.appendChild(Id4);

        // Add IBAN element to Id4
        appendChildWithTextContent(doc, Id4, "IBAN", "NL61NNBA0719053412");
        
        //CdtTrfTxInf
        Element CdtTrfTxInf = doc.createElement("CdtTrfTxInf");
        FIToFICstmrCdtTrf.appendChild(CdtTrfTxInf);
        
        Element PmtId = doc.createElement("PmtId");
        CdtTrfTxInf.appendChild(PmtId);
        
        appendChildWithTextContent(doc, PmtId, "InstrId", "RETST2023080407XX");
        appendChildWithTextContent(doc, PmtId, "EndToEndId", "RETST2023080407XX");
        appendChildWithTextContent(doc, PmtId, "UETR", "69648ec4-c4b0-4f20-879a-ae2023080407");
        appendChildWithTextContent(doc, PmtId, "ClrSysRef", "1641401");
        
        
        appendChildWithTextContent(doc, CdtTrfTxInf, "IntrBkSttlmAmt", "70.50", "Ccy", "EUR");
        appendChildWithTextContent(doc, CdtTrfTxInf, "IntrBkSttlmDt", "2023-08-04");
        appendChildWithTextContent(doc, CdtTrfTxInf, "SttlmPrty", "HIGH");
        
        //SttlmTmIndctn
        Element SttlmTmIndctn = doc.createElement("SttlmTmIndctn");
        CdtTrfTxInf.appendChild(SttlmTmIndctn);
        
        Element CdtDtTm = doc.createElement("CdtDtTm");
        SttlmTmIndctn.appendChild(CdtDtTm);
        CdtDtTm.setTextContent("2023-08-04T07:43:25.407+00:00");
        
        appendChildWithTextContent(doc, CdtTrfTxInf, "InstdAmt", "70.50", "Ccy", "EUR");
        appendChildWithTextContent(doc, CdtTrfTxInf, "ChrgBr", "CRED");
        
        //InstgAgt
        Element InstgAgt = doc.createElement("InstgAgt");
        CdtTrfTxInf.appendChild(InstgAgt);
        
        Element FinInstnId0 = doc.createElement("FinInstnId");
        InstgAgt.appendChild(FinInstnId0);
        
        Element BICFI = doc.createElement("BICFI");
        FinInstnId0.appendChild(BICFI);
        BICFI.setTextContent("ABNANL2AXXX");
        
      //InstgAgt
        Element InstdAgt = doc.createElement("InstdAgt");
        CdtTrfTxInf.appendChild(InstdAgt);
        
        Element FinInstnId01 = doc.createElement("FinInstnId");
        InstdAgt.appendChild(FinInstnId01);
        
        Element BICFI1 = doc.createElement("BICFI");
        FinInstnId01.appendChild(BICFI1);
        BICFI1.setTextContent("NNBANL2GXXX");
        
        //Dbtr
        
        Element Dbtr1 = doc.createElement("Dbtr");
        CdtTrfTxInf.appendChild(Dbtr1);
        
        Element Nm = doc.createElement("Nm");
        Dbtr1.appendChild(Nm);
        Nm.setTextContent("F.DEBITCUST");
        
        Element PstlAdr0 = doc.createElement("PstlAdr");
        Dbtr1.appendChild(PstlAdr0);
        
        appendChildWithTextContent(doc, PstlAdr0, "StrtNm", "OUDEGRACHT 99");
        appendChildWithTextContent(doc, PstlAdr0, "TwnNm", "UTRE9HT");
        appendChildWithTextContent(doc, PstlAdr0, "Ctry", "NL");
        appendChildWithTextContent(doc, Dbtr1, "CtryOfRes", "NL");
        
        //DbtrAcct
        Element DbtrAcct0 = doc.createElement("DbtrAcct");
        CdtTrfTxInf.appendChild(DbtrAcct0);
        
        Element Id0 = doc.createElement("Id");
        DbtrAcct0.appendChild(Id0);
        
        Element Othr0 = doc.createElement("Othr");
        Id0.appendChild(Othr0);
        
        Element Id1 = doc.createElement("Id");
        Othr0.appendChild(Id1);
        
        Id1.setTextContent("NL91ABNA0417164300");
        
        
      //InstgAgt
        Element DbtrAgt1 = doc.createElement("DbtrAgt");
        CdtTrfTxInf.appendChild(DbtrAgt1);
        
        Element FinInstnId011 = doc.createElement("FinInstnId");
        DbtrAgt1.appendChild(FinInstnId011);
        
        Element BICFI11 = doc.createElement("BICFI");
        FinInstnId011.appendChild(BICFI11);
        BICFI11.setTextContent("ABNANL2AXXX");
        
      //InstgAgt
        Element CdtrAgt1 = doc.createElement("CdtrAgt");
        CdtTrfTxInf.appendChild(CdtrAgt1);
        
        Element FinInstnId010 = doc.createElement("FinInstnId");
        CdtrAgt1.appendChild(FinInstnId010);
        
        Element BICFI10 = doc.createElement("BICFI");
        FinInstnId010.appendChild(BICFI10);
        BICFI10.setTextContent("NNBANL2GXXX");
        
        Element Cdtr0 = doc.createElement("Cdtr");
        CdtTrfTxInf.appendChild(Cdtr0);
        Element Nm0 = doc.createElement("Nm");
        Cdtr0.appendChild(Nm0);
        Nm0.setTextContent("F. FIRSTCUSTOMER");
        
        Element PstlAdr12 = doc.createElement("PstlAdr");
        Cdtr0.appendChild(PstlAdr12);
        appendChildWithTextContent(doc, PstlAdr12, "StrtNm", "OUDEGRACHT 500");
        appendChildWithTextContent(doc, PstlAdr12, "TwnNm", "UTRECHT");
        appendChildWithTextContent(doc, PstlAdr12, "Ctry", "NL");
      
     // Create CdtrAcct element
        Element CdtrAcct0 = doc.createElement("CdtrAcct");
        CdtTrfTxInf.appendChild(CdtrAcct0);

        // Add Id element to CdtrAcct
        Element Id40 = doc.createElement("Id");
        CdtrAcct0.appendChild(Id40);

        // Add IBAN element to Id4
        appendChildWithTextContent(doc, Id40, "IBAN", "NL66NNBA1000000533");
        
        //RmtInf
        Element RmtInf0 = doc.createElement("RmtInf");
        CdtTrfTxInf.appendChild(RmtInf0);
        appendChildWithTextContent(doc, RmtInf0, "Ustrd", "-NP39337112 /REC/TEST DATA FOR VER2");
        
        
        
        
        

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("TestCredFile.xml"));
        transformer.transform(source, result);


    }

    // Utility method to create and append an element with text content to a parent element
    private static void appendChildWithTextContent(Document doc, Element parent, String tagName, String textContent) {
        Element element = doc.createElement(tagName);
        element.setTextContent(textContent);
        parent.appendChild(element);
    }
    private static void appendChildWithTextContent(Document doc, Element parent, String tagName, String textContent, String attributeName, String attributeValue) {
        Element element = doc.createElement(tagName);
        element.setTextContent(textContent);
        element.setAttribute(attributeName, attributeValue);
        parent.appendChild(element);
    }
}
