package XMLCreation;

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

public class CTRTEP {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element rootelement = doc.createElement("Document");
        String ppp = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.08";
        String bbb = "http://www.w3.org/2001/XMLSchema-instance";
        rootelement.setAttribute("xmlns", ppp);
        rootelement.setAttribute("xmlns:xsi", bbb);
        doc.appendChild(rootelement);
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String TempMsgIdText = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(TempMsgIdText);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat aaa = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat ccc = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat ddd = new SimpleDateFormat("HHmmss");
        String formattedDate = sdf.format(date);
        String OnlyDate = aaa.format(date);
        String currdate = formattedDate.replace(" ", "T");
        String Messagetime1 =ccc.format(date);
        String Messagetime2 =ddd.format(date);
        String Messagetime =Messagetime1+"T"+Messagetime2;
        System.out.println(currdate);
        System.out.println(OnlyDate);
        System.out.println(Messagetime);
        String MsgIdText ="INNDNL2U"+Messagetime ;
        System.out.println(MsgIdText);
        
        //Adding elements to XML
        Element PmtRtr = doc.createElement("PmtRtr");
        rootelement.appendChild(PmtRtr);

        Element GrpHdr = doc.createElement("GrpHdr");
        PmtRtr.appendChild(GrpHdr);

        Element MsgId = doc.createElement("MsgId");
        GrpHdr.appendChild(MsgId);
        MsgId.setTextContent(MsgIdText);
        Element CreDtTm = doc.createElement("CreDtTm");
        GrpHdr.appendChild(CreDtTm);
        CreDtTm.setTextContent(currdate);
        Element NbOfTxs = doc.createElement("NbOfTxs");
        GrpHdr.appendChild(NbOfTxs);
        NbOfTxs.setTextContent("1");
        Element TtlRtrdIntrBkSttlmAmt = doc.createElement("TtlRtrdIntrBkSttlmAmt");
        GrpHdr.appendChild(TtlRtrdIntrBkSttlmAmt);
        TtlRtrdIntrBkSttlmAmt.setAttribute("Ccy","EUR");
        TtlRtrdIntrBkSttlmAmt.setTextContent("525");
        Element IntrBkSttlmDt = doc.createElement("IntrBkSttlmDt");
        GrpHdr.appendChild(IntrBkSttlmDt);
        IntrBkSttlmDt.setTextContent(OnlyDate);
        
        //Elements inside SttlmInf
        Element SttlmInf = doc.createElement("SttlmInf");
        Element SttlmMtd = doc.createElement("SttlmMtd");
        Element ClrSys = doc.createElement("ClrSys");
        Element Prtry = doc.createElement("Prtry");

        GrpHdr.appendChild(SttlmInf);
        SttlmInf.appendChild(SttlmMtd);
        SttlmMtd.setTextContent("CLRG");
        SttlmInf.appendChild(ClrSys);
        ClrSys.appendChild(Prtry);
        Prtry.setTextContent("ST2");
        
        //Elemet inside InstgAgt
        Element InstgAgt = doc.createElement("InstgAgt");
        Element FinInstnId_SuperTag1 = doc.createElement("FinInstnId");
        Element BICFI = doc.createElement("BIC");
        GrpHdr.appendChild(InstgAgt);
        InstgAgt.appendChild(FinInstnId_SuperTag1);
        FinInstnId_SuperTag1.appendChild(BICFI);
        BICFI.setTextContent("NNBANL2G");
        
        

        Element TxInf = doc.createElement("TxInf");
        Element RtrId = doc.createElement("RtrId");
        TxInf.appendChild(RtrId);
        RtrId.setTextContent("444402001");
        
        //tag OrgnlGrpInf
        PmtRtr.appendChild(TxInf);
        Element OrgnlGrpInf = doc.createElement("OrgnlGrpInf");
        TxInf.appendChild(OrgnlGrpInf);
        Element OrgnlMsgId = doc.createElement("OrgnlMsgId");
        OrgnlGrpInf.appendChild(OrgnlMsgId);
        OrgnlMsgId.setTextContent("PI231160XX6B11ZV");
        
        //Create Element for OrgnlMsgNmId
        Element OrgnlMsgNmId = doc.createElement("OrgnlMsgNmId");
        OrgnlGrpInf.appendChild(OrgnlMsgNmId);
        OrgnlMsgNmId.setTextContent("pacs.008.001.02");
        
      //tag OrgnlGrpInf
        PmtRtr.appendChild(TxInf);
        Element OrgnlInstrId = doc.createElement("OrgnlInstrId");
        TxInf.appendChild(OrgnlInstrId);
        OrgnlInstrId.setTextContent("NOTPROVIDED");
        
      //tag OrgnlEndToEndId  
        PmtRtr.appendChild(TxInf);
        Element OrgnlEndToEndId = doc.createElement("OrgnlEndToEndId");
        TxInf.appendChild(OrgnlEndToEndId);
        OrgnlEndToEndId.setTextContent("BNK23116DJFLJFGK");
        
        //Tag OrgnlTxId
        PmtRtr.appendChild(TxInf);
        Element OrgnlTxId = doc.createElement("OrgnlTxId");
        TxInf.appendChild(OrgnlTxId);
        OrgnlTxId.setTextContent("BNK23116DJFLJFGK");
        
        //Tag OrgnlIntrBkSttlmAmt
        Element OrgnlIntrBkSttlmAmt = doc.createElement("OrgnlIntrBkSttlmAmt");
        TxInf.appendChild(OrgnlIntrBkSttlmAmt);
        OrgnlIntrBkSttlmAmt.setAttribute("Ccy","EUR");
        OrgnlIntrBkSttlmAmt.setTextContent("103.00");
        
        //Tag RtrdIntrBkSttlmAmt
        Element RtrdIntrBkSttlmAmt = doc.createElement("RtrdIntrBkSttlmAmt");
        TxInf.appendChild(RtrdIntrBkSttlmAmt);
        RtrdIntrBkSttlmAmt.setAttribute("Ccy","EUR");
        RtrdIntrBkSttlmAmt.setTextContent("103.00");
        
        //tag ChrgsInf
        PmtRtr.appendChild(TxInf);
        Element ChrgsInf = doc.createElement("ChrgsInf");
        TxInf.appendChild(ChrgsInf);
        Element Amt = doc.createElement("Amt");
        ChrgsInf.appendChild(Amt);
        Amt.setAttribute("Ccy","EUR");
        Amt.setTextContent("0");
        
        //Tag Pty
        Element Pty = doc.createElement("Pty");
        ChrgsInf.appendChild(Pty);
        Element FinInstnId = doc.createElement("FinInstnId");
        Pty.appendChild(FinInstnId);
        Element BIC = doc.createElement("BIC");
        FinInstnId.appendChild(BIC);
        BIC.setTextContent("NNBANL2G");
        
        // //tag RtrRsnInf
        Element RtrRsnInf = doc.createElement("RtrRsnInf");
        TxInf.appendChild(RtrRsnInf);
        Element Orgtr = doc.createElement("Orgtr");
        RtrRsnInf.appendChild(Orgtr);
        Element Id = doc.createElement("Id");
        Orgtr.appendChild(Id);
        Element OrgId = doc.createElement("OrgId");
        Id.appendChild(OrgId);
        Element BICOrBEI = doc.createElement("BICOrBEI");
        OrgId.appendChild(BICOrBEI);
        BICOrBEI.setTextContent("NNBANL2G");
        
        //Tag
        Element Rsn = doc.createElement("Rsn");
        RtrRsnInf.appendChild(Rsn);
        Element Cd1 = doc.createElement("Cd");
        Rsn.appendChild(Cd1);
        Cd1.setTextContent("AC01");
        
        // //tag OrgnlTxRef
        Element OrgnlTxRef = doc.createElement("OrgnlTxRef");
        TxInf.appendChild(OrgnlTxRef);
        Element IntrBkSttlmDt1 = doc.createElement("IntrBkSttlmDt");
        OrgnlTxRef.appendChild(IntrBkSttlmDt1);
        IntrBkSttlmDt1.setTextContent(OnlyDate);
        
        //Elements inside SttlmInf
        Element SttlmInf1 = doc.createElement("SttlmInf");
        OrgnlTxRef.appendChild(SttlmInf1);
        Element SttlmMtd1 = doc.createElement("SttlmMtd");
        SttlmInf1.appendChild(SttlmMtd1);
        SttlmMtd1.setTextContent("CLRG");
        
       //Create Element for PmtTpInf
        
        
        Element PmtTpInf1 = doc.createElement("PmtTpInf");
        OrgnlTxRef.appendChild(PmtTpInf1);
        Element SvcLvl1 = doc.createElement("SvcLvl");
        PmtTpInf1.appendChild(SvcLvl1);
        Element Cd2 = doc.createElement("Cd");
        SvcLvl1.appendChild(Cd2);
        Cd2.setTextContent("SEPA");
        
        //
//Create Element for PmtTpInf
        
        
        Element RmtInf = doc.createElement("RmtInf");
        OrgnlTxRef.appendChild(RmtInf);
        Element Ustrd = doc.createElement("Ustrd");
        RmtInf.appendChild(Ustrd);
        Ustrd.setTextContent("Rmsg from EP+ SCT");
        
        //Dbtr
        
        Element Dbtr = doc.createElement("Dbtr");
        OrgnlTxRef.appendChild(Dbtr);
        Element Nm = doc.createElement("Nm");
        Dbtr.appendChild(Nm);
        Nm.setTextContent("Sabari");

    
        
      
        Date date1 = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat aaa1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat ccc1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat ddd1 = new SimpleDateFormat("HHmmss");
        String formattedDate1 = sdf1.format(date);
        String OnlyDate1 = aaa1.format(date);
        String currdate1 = formattedDate.replace(" ", "T");
        String Messagetime11 =ccc1.format(date);
        String Messagetime21 =ddd1.format(date);
        String Messagetime111 =Messagetime11+"T"+Messagetime21;
        String MsgIdText1 ="000000000000"+Messagetime111 ;
        System.out.println(MsgIdText1);
       // InstrId.setTextContent(MsgIdText1);
        Element EndToEndId = doc.createElement("EndToEndId");

        String EndToEndIdText = "E2E"+Messagetime111;
        EndToEndId.setTextContent(EndToEndIdText);
        Element TxId = doc.createElement("TxId");
        TxId.setTextContent("TxId"+Messagetime111);
        
        //DbtrAcct
        Element DbtrAcct = doc.createElement("DbtrAcct");
        OrgnlTxRef.appendChild(DbtrAcct);
        Element Id1 = doc.createElement("Id");
        DbtrAcct.appendChild(Id1);
        Element IBAN = doc.createElement("IBAN");
        Id1.appendChild(IBAN);
        IBAN.setTextContent("ES3300190029114010023693");
        
        //DbtrAgt
        Element DbtrAgt = doc.createElement("DbtrAgt");
        OrgnlTxRef.appendChild(DbtrAgt);
        Element FinInstnId1 = doc.createElement("FinInstnId");
        DbtrAgt.appendChild(FinInstnId1);
        Element BIC1 = doc.createElement("BIC");
        FinInstnId1.appendChild(BIC1);
        BIC1.setTextContent("DEUTESBB");
        
        //CdtrAgt
        Element CdtrAgt = doc.createElement("CdtrAgt");
        OrgnlTxRef.appendChild(CdtrAgt);
        Element FinInstnId2 = doc.createElement("FinInstnId");
        CdtrAgt.appendChild(FinInstnId2);
        Element BIC2 = doc.createElement("BIC");
        FinInstnId2.appendChild(BIC2);
        BIC2.setTextContent("NNBANL2G");
        
      //Element inside Cdtr
        Element Cdtr1 = doc.createElement("Cdtr");
        OrgnlTxRef.appendChild(Cdtr1);
        Element Nm2 = doc.createElement("Nm");
        Cdtr1.appendChild(Nm2);
        Nm2.setTextContent("Corry");
        
        // Element inside  CdtrAcct
        Element CdtrAcct1 = doc.createElement("CdtrAcct");
        OrgnlTxRef.appendChild(CdtrAcct1);
        Element ID6 = doc.createElement("Id");
        CdtrAcct1.appendChild(ID6);
        Element IBAN1 = doc.createElement("IBAN");
        ID6.appendChild(IBAN1);
        IBAN1.setTextContent("NL54NNBA2040114580");
        
        
        
       
       


        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("CTRTEPPACS008.xml"));
        transformer.transform(source, result);


    }



}



