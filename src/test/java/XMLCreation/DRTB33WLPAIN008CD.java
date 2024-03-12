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

public class DRTB33WLPAIN008CD {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element rootelement = doc.createElement("Document");
        String ppp = "urn:iso:std:iso:20022:tech:xsd:pacs.004.001.09";
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
        TtlRtrdIntrBkSttlmAmt.setTextContent("14.00");
        Element IntrBkSttlmDt = doc.createElement("IntrBkSttlmDt");
        GrpHdr.appendChild(IntrBkSttlmDt);
        IntrBkSttlmDt.setTextContent(OnlyDate);
        
        //Elements inside SttlmInf
        Element SttlmInf = doc.createElement("SttlmInf");
        GrpHdr.appendChild(SttlmInf);
        Element SttlmMtd = doc.createElement("SttlmMtd");
        SttlmInf.appendChild(SttlmMtd);
        SttlmMtd.setTextContent("CLRG");
        Element ClrSys = doc.createElement("ClrSys");
        SttlmInf.appendChild(ClrSys);
        Element Cd1_New = doc.createElement("Cd");
        ClrSys.appendChild(Cd1_New);
        Cd1_New.setTextContent("INC");  
        
//Tag InstdAgt
        
        Element InstdAgt = doc.createElement("InstdAgt");
        GrpHdr.appendChild(InstdAgt);
        Element FinInstnId1_New = doc.createElement("FinInstnId");
        InstdAgt.appendChild(FinInstnId1_New);
        Element BICFI1_1 = doc.createElement("BICFI");
        FinInstnId1_New.appendChild(BICFI1_1);
        BICFI1_1.setTextContent("NNBANL2G");

        Element TxInf = doc.createElement("TxInf");
        Element RtrId = doc.createElement("RtrId");
        TxInf.appendChild(RtrId);
        RtrId.setTextContent("RtrId"+OnlyDate+"INNDNL61488");
        
        //tag OrgnlGrpInf
        PmtRtr.appendChild(TxInf);
        Element OrgnlGrpInf = doc.createElement("OrgnlGrpInf");
        TxInf.appendChild(OrgnlGrpInf);
        Element OrgnlMsgId = doc.createElement("OrgnlMsgId");
        OrgnlGrpInf.appendChild(OrgnlMsgId);
        OrgnlMsgId.setTextContent("SBD23047GDL9V");
        
        //Create Element for OrgnlMsgNmId
        Element OrgnlMsgNmId = doc.createElement("OrgnlMsgNmId");
        OrgnlGrpInf.appendChild(OrgnlMsgNmId);
        OrgnlMsgNmId.setTextContent("pacs.003.001.08");
        
      //tag OrgnlGrpInf
        PmtRtr.appendChild(TxInf);
        Element OrgnlInstrId = doc.createElement("OrgnlInstrId");
        TxInf.appendChild(OrgnlInstrId);
        OrgnlInstrId.setTextContent("NOTPROVIDED");
        
        
      //tag OrgnlEndToEndId  
        PmtRtr.appendChild(TxInf);
        Element OrgnlEndToEndId = doc.createElement("OrgnlEndToEndId");
        TxInf.appendChild(OrgnlEndToEndId);
        OrgnlEndToEndId.setTextContent("3979499");
        
        //Tag OrgnlTxId
        PmtRtr.appendChild(TxInf);
        Element OrgnlTxId = doc.createElement("OrgnlTxId");
        TxInf.appendChild(OrgnlTxId);
        OrgnlTxId.setTextContent("BNK2304700JJKFGH");
        
        //Tag OrgnlIntrBkSttlmAmt
        Element OrgnlIntrBkSttlmAmt = doc.createElement("OrgnlIntrBkSttlmAmt");
        TxInf.appendChild(OrgnlIntrBkSttlmAmt);
        OrgnlIntrBkSttlmAmt.setAttribute("Ccy","EUR");
        OrgnlIntrBkSttlmAmt.setTextContent("33.00");
        
        //Tag RtrdIntrBkSttlmAmt
        Element RtrdIntrBkSttlmAmt = doc.createElement("RtrdIntrBkSttlmAmt");
        TxInf.appendChild(RtrdIntrBkSttlmAmt);
        RtrdIntrBkSttlmAmt.setAttribute("Ccy","EUR");
        RtrdIntrBkSttlmAmt.setTextContent("33.00");
        
        Element ChrgBr2 = doc.createElement("ChrgBr");
        TxInf.appendChild(ChrgBr2);
        ChrgBr2.setTextContent("SLEV");
        
//Tag InstdAgt
        
        Element InstgAgt = doc.createElement("InstgAgt");
        TxInf.appendChild(InstgAgt);
        Element FinInstnId1 = doc.createElement("FinInstnId");
        InstgAgt.appendChild(FinInstnId1);
        Element BICFI1 = doc.createElement("BICFI");
        FinInstnId1.appendChild(BICFI1);
        BICFI1.setTextContent("DEUTESBB");
        
        // //tag RtrRsnInf
        Element RtrRsnInf = doc.createElement("RtrRsnInf");
        TxInf.appendChild(RtrRsnInf);
        Element Orgtr = doc.createElement("Orgtr");
        RtrRsnInf.appendChild(Orgtr);
        Element Id = doc.createElement("Id");
        Orgtr.appendChild(Id);
        Element OrgId = doc.createElement("OrgId");
        Id.appendChild(OrgId);
        Element BICOrBEI = doc.createElement("AnyBIC");
        OrgId.appendChild(BICOrBEI);
        BICOrBEI.setTextContent("DEUTESBB");
        
        //Tag
        Element Rsn = doc.createElement("Rsn");
        RtrRsnInf.appendChild(Rsn);
        Element Cd111 = doc.createElement("Cd");
        Rsn.appendChild(Cd111);
        Cd111.setTextContent("AC01");
        
        Element AddtlInf = doc.createElement("AddtlInf");
        RtrRsnInf.appendChild(AddtlInf);
        AddtlInf.setTextContent("/STTLMREF/CC60811001501261");
        
        Element OrgnlTxRef = doc.createElement("OrgnlTxRef");
        TxInf.appendChild(OrgnlTxRef);
        
        // //tag OrgnlTxRef
      
        Element IntrBkSttlmDt1 = doc.createElement("IntrBkSttlmDt");
        OrgnlTxRef.appendChild(IntrBkSttlmDt1);
        IntrBkSttlmDt1.setTextContent(OnlyDate);
        
        
        //Elements inside SttlmInf
        Element SttlmInf1 = doc.createElement("SttlmInf");
        OrgnlTxRef.appendChild(SttlmInf1);
        Element SttlmMtd1 = doc.createElement("SttlmMtd");
        SttlmInf1.appendChild(SttlmMtd1);
        SttlmMtd1.setTextContent("CLRG");
        Element ClrSys1 = doc.createElement("ClrSys");
        SttlmInf1.appendChild(ClrSys1);
        Element Cd1_New1 = doc.createElement("Cd");
        ClrSys1.appendChild(Cd1_New1);
        Cd1_New1.setTextContent("INC"); 
        
        //Create Element for OrgnlMsgNmId
        Element PmtTpInf = doc.createElement("PmtTpInf");
        OrgnlTxRef.appendChild(PmtTpInf);
        Element SvcLvl = doc.createElement("SvcLvl");
        PmtTpInf.appendChild(SvcLvl);
        Element Cd = doc.createElement("Cd");
        SvcLvl.appendChild(Cd);
        Cd.setTextContent("SEPA");
        
        //RmtInf
        Element RmtInf1 = doc.createElement("RmtInf");
        OrgnlTxRef.appendChild(RmtInf1);
        Element Ustrd1 = doc.createElement("Ustrd");
        RmtInf1.appendChild(Ustrd1);
        Ustrd1.setTextContent("Qucpaaf pukeg KIA");
        
        //Dbtr

        Element Dbtr = doc.createElement("Dbtr");
        OrgnlTxRef.appendChild(Dbtr);
        Element Pty1 = doc.createElement("Pty");
        Dbtr.appendChild(Pty1);
        Element Nm11 = doc.createElement("Nm");
        Pty1.appendChild(Nm11);
        Nm11.setTextContent("T. Vsnmsno s/n Q.A.Q. Awmmzwzo");
        
        //DbtrAcct
        Element DbtrAcct = doc.createElement("DbtrAcct");
        OrgnlTxRef.appendChild(DbtrAcct);
        Element Id11 = doc.createElement("Id");
        DbtrAcct.appendChild(Id11);
        Element IBAN1 = doc.createElement("IBAN");
        Id11.appendChild(IBAN1);
        IBAN1.setTextContent("ES3300190029114010023693");
        
//      //DbtrAgt
      Element DbtrAgt = doc.createElement("DbtrAgt");
      OrgnlTxRef.appendChild(DbtrAgt);
      Element FinInstnId11 = doc.createElement("FinInstnId");
      DbtrAgt.appendChild(FinInstnId11);
      Element BIC1 = doc.createElement("BICFI");
      FinInstnId11.appendChild(BIC1);
      BIC1.setTextContent("DEUTESBB");
        
      
      //CdtrAgt
      Element CdtrAgt = doc.createElement("CdtrAgt");
      OrgnlTxRef.appendChild(CdtrAgt);
      Element FinInstnId2 = doc.createElement("FinInstnId");
      CdtrAgt.appendChild(FinInstnId2);
      Element BIC2 = doc.createElement("BICFI");
      FinInstnId2.appendChild(BIC2);
      BIC2.setTextContent("NNBANL2G");
        
      //Element inside Cdtr
      Element Cdtr1 = doc.createElement("Cdtr");
      OrgnlTxRef.appendChild(Cdtr1);
      Element Pty = doc.createElement("Pty");
      Cdtr1.appendChild(Pty);
      Element Nm22 = doc.createElement("Nm");
      Pty.appendChild(Nm22);
      Nm22.setTextContent("Pxcyoixxs Psusvxxiusi Zxih");
       
      // Element inside  CdtrAcct
      Element CdtrAcct1 = doc.createElement("CdtrAcct");
      OrgnlTxRef.appendChild(CdtrAcct1);
      Element ID6 = doc.createElement("Id");
      CdtrAcct1.appendChild(ID6);
      Element IBAN11 = doc.createElement("IBAN");
      ID6.appendChild(IBAN11);
      IBAN11.setTextContent("NL02NNBA3188204897");
        
     
        
       
      
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

       


        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("DRTB33.xml"));
        transformer.transform(source, result);


    }



}



