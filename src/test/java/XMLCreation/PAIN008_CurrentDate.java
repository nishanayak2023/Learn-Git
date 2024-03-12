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

public class PAIN008_CurrentDate {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element rootelement = doc.createElement("Document");
        String ppp = "urn:iso:std:iso:20022:tech:xsd:pain.008.001.08";
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
        String MsgIdText ="EP15841"+Messagetime ;
        System.out.println(MsgIdText);
        
        //Adding elements to XML
        Element CstmrDrctDbtInitn = doc.createElement("CstmrDrctDbtInitn");
        rootelement.appendChild(CstmrDrctDbtInitn);

        Element GrpHdr = doc.createElement("GrpHdr");
        CstmrDrctDbtInitn.appendChild(GrpHdr);

        Element MsgId = doc.createElement("MsgId");
        GrpHdr.appendChild(MsgId);
        MsgId.setTextContent(MsgIdText);
        Element CreDtTm = doc.createElement("CreDtTm");
        GrpHdr.appendChild(CreDtTm);
        CreDtTm.setTextContent(currdate);
        
        Element NbOfTxs = doc.createElement("NbOfTxs");
        GrpHdr.appendChild(NbOfTxs);
        NbOfTxs.setTextContent("5");

        Element CtrlSum = doc.createElement("CtrlSum");
        GrpHdr.appendChild(CtrlSum);
        CtrlSum.setTextContent("166");
        


        
//      //Elemet inside InitgPty
        Element InitgPty = doc.createElement("InitgPty");
        Element Nm = doc.createElement("Nm");
        GrpHdr.appendChild(InitgPty);
        InitgPty.appendChild(Nm);
        Nm.setTextContent("Poor Bank");
        
//      //Elemet inside PstlAdr
        Element PstlAdr = doc.createElement("PstlAdr");
        InitgPty.appendChild(PstlAdr);
        Element Dept = doc.createElement("Dept");
        PstlAdr.appendChild(Dept);
        Dept.setTextContent("Bank");
       
        Element SubDept = doc.createElement("SubDept");
        PstlAdr.appendChild(SubDept);
        SubDept.setTextContent("Payments");
        
        Element StrtNm = doc.createElement("StrtNm");
        PstlAdr.appendChild(StrtNm);
        StrtNm.setTextContent("Milkyway");
        
        Element BldgNb = doc.createElement("BldgNb");
        PstlAdr.appendChild(BldgNb);
        BldgNb.setTextContent("35");
        
        Element BldgNm = doc.createElement("BldgNm");
        PstlAdr.appendChild(BldgNm);
        BldgNm.setTextContent("Haagsepoort");
        
        Element Flr = doc.createElement("Flr");
        PstlAdr.appendChild(Flr);
        Flr.setTextContent("10");
        
        Element PstBx = doc.createElement("PstBx");
        PstlAdr.appendChild(PstBx);
        PstBx.setTextContent("1000");
        
        Element Room = doc.createElement("Room");
        PstlAdr.appendChild(Room);
        Room.setTextContent("D");
        
        Element PstCd = doc.createElement("PstCd");
        PstlAdr.appendChild(PstCd);
        PstCd.setTextContent("2595AK");
        
        Element TwnNm = doc.createElement("TwnNm");
        PstlAdr.appendChild(TwnNm);
        TwnNm.setTextContent("Den Haag");
        
        Element TwnLctnNm = doc.createElement("TwnLctnNm");
        PstlAdr.appendChild(TwnLctnNm);
        TwnLctnNm.setTextContent("Muziekbuurt");
        
        Element DstrctNm = doc.createElement("DstrctNm");
        PstlAdr.appendChild(DstrctNm);
        DstrctNm.setTextContent("Rijswijk");
        
        Element CtrySubDvsn = doc.createElement("CtrySubDvsn");
        PstlAdr.appendChild(CtrySubDvsn);
        CtrySubDvsn.setTextContent("Zuid-Holland");
        
        Element Ctry = doc.createElement("Ctry");
        PstlAdr.appendChild(Ctry);
        Ctry.setTextContent("NL");
        
        //Elemet inside InitgPty
        Element Id = doc.createElement("Id");
        InitgPty.appendChild(Id);
        Element OrgId = doc.createElement("OrgId");
        Id.appendChild(OrgId);
        Element AnyBIC = doc.createElement("AnyBIC");
        OrgId.appendChild(AnyBIC);
        AnyBIC.setTextContent("NNBANL2G");
       
       
        
        ////tag PmtInf
        Element PmtInf = doc.createElement("PmtInf");
        CstmrDrctDbtInitn.appendChild(PmtInf);
        Element PmtInfId = doc.createElement("PmtInfId");
        PmtInf.appendChild(PmtInfId);
        PmtInfId.setTextContent("7012280");
        
        Element PmtMtd = doc.createElement("PmtMtd");
        PmtInf.appendChild(PmtMtd);
        PmtMtd.setTextContent("DD");
        
        Element BtchBookg = doc.createElement("BtchBookg");
        PmtInf.appendChild(BtchBookg);
        BtchBookg.setTextContent("false");
        
        Element NbOfTxs1 = doc.createElement("NbOfTxs");
        PmtInf.appendChild(NbOfTxs1);
        NbOfTxs1.setTextContent("1");
        
        Element CtrlSum1 = doc.createElement("CtrlSum");
        PmtInf.appendChild(CtrlSum1);
        CtrlSum1.setTextContent("31");
        
      //Create Element for OrgnlMsgNmId
        Element PmtTpInf = doc.createElement("PmtTpInf");
        PmtInf.appendChild(PmtTpInf);
        Element SvcLvl = doc.createElement("SvcLvl");
        PmtTpInf.appendChild(SvcLvl);
        Element Cd = doc.createElement("Cd");
        SvcLvl.appendChild(Cd);
        Cd.setTextContent("SEPA");
        
        //Create Element for PmtTpInf
        Element LclInstrm = doc.createElement("LclInstrm");
        PmtTpInf.appendChild(LclInstrm);
        Element Cd1 = doc.createElement("Cd");
        LclInstrm.appendChild(Cd1);
        Cd1.setTextContent("CORE");
        
        Element SeqTp = doc.createElement("SeqTp");
        PmtTpInf.appendChild(SeqTp);
        SeqTp.setTextContent("RCUR");
        
        Element ReqdColltnDt = doc.createElement("ReqdColltnDt");
        PmtInf.appendChild(ReqdColltnDt);
        ReqdColltnDt.setTextContent(OnlyDate);
        
        Element Cdtr = doc.createElement("Cdtr");
        PmtInf.appendChild(Cdtr);
        Element Nm1 = doc.createElement("Nm");
        Cdtr.appendChild(Nm1);
        Nm1.setTextContent("Pxcyoixxs Psusvxxiusi Zxih");
        
        //Elemet inside PstlAdr
        Element PstlAdr1 = doc.createElement("PstlAdr");
        Cdtr.appendChild(PstlAdr1);
        Element Dept1 = doc.createElement("Dept");
        PstlAdr1.appendChild(Dept1);
        Dept1.setTextContent("Bank");
       
        Element SubDept1 = doc.createElement("SubDept");
        PstlAdr1.appendChild(SubDept1);
        SubDept1.setTextContent("Payments");
        
        Element StrtNm1 = doc.createElement("StrtNm");
        PstlAdr1.appendChild(StrtNm1);
        StrtNm1.setTextContent("Milkyway");
        
        Element BldgNb1 = doc.createElement("BldgNb");
        PstlAdr1.appendChild(BldgNb1);
        BldgNb1.setTextContent("35");
        
        Element BldgNm1 = doc.createElement("BldgNm");
        PstlAdr1.appendChild(BldgNm1);
        BldgNm1.setTextContent("Haagsepoort");
        
        Element Flr1 = doc.createElement("Flr");
        PstlAdr1.appendChild(Flr1);
        Flr1.setTextContent("10");
        
        Element PstBx1 = doc.createElement("PstBx");
        PstlAdr1.appendChild(PstBx1);
        PstBx1.setTextContent("1000");
        
        Element Room1 = doc.createElement("Room");
        PstlAdr1.appendChild(Room1);
        Room1.setTextContent("D");
        
        Element PstCd1 = doc.createElement("PstCd");
        PstlAdr1.appendChild(PstCd1);
        PstCd1.setTextContent("2595AK");
        
        Element TwnNm1 = doc.createElement("TwnNm");
        PstlAdr1.appendChild(TwnNm1);
        TwnNm1.setTextContent("Den Haag");
        
        Element TwnLctnNm1 = doc.createElement("TwnLctnNm");
        PstlAdr1.appendChild(TwnLctnNm1);
        TwnLctnNm1.setTextContent("Muziekbuurt");
        
        Element DstrctNm1 = doc.createElement("DstrctNm");
        PstlAdr1.appendChild(DstrctNm1);
        DstrctNm1.setTextContent("Rijswijk");
        
        Element CtrySubDvsn1 = doc.createElement("CtrySubDvsn");
        PstlAdr1.appendChild(CtrySubDvsn1);
        CtrySubDvsn1.setTextContent("Zuid-Holland");
        
        Element Ctry1 = doc.createElement("Ctry");
        PstlAdr1.appendChild(Ctry1);
        Ctry1.setTextContent("NL");
        
//////Element inside  CdtrAcct
Element CdtrAcct1 = doc.createElement("CdtrAcct");
PmtInf.appendChild(CdtrAcct1);
Element ID = doc.createElement("Id");
CdtrAcct1.appendChild(ID);
Element IBAN = doc.createElement("IBAN");
ID.appendChild(IBAN);
IBAN.setTextContent("NL61NNBA0719053412");

//////CdtrAgt
Element CdtrAgt = doc.createElement("CdtrAgt");
PmtInf.appendChild(CdtrAgt);
Element FinInstnId2_n = doc.createElement("FinInstnId");
CdtrAgt.appendChild(FinInstnId2_n);
Element BICFI2 = doc.createElement("BICFI");
FinInstnId2_n.appendChild(BICFI2);
BICFI2.setTextContent("NNBANL2G");

Element ChrgBr = doc.createElement("ChrgBr");
PmtInf.appendChild(ChrgBr);
ChrgBr.setTextContent("SLEV");

Element CdtrSchmeId = doc.createElement("CdtrSchmeId");
PmtInf.appendChild(CdtrSchmeId);
Element Id1 = doc.createElement("Id");
CdtrSchmeId.appendChild(Id1);
Element PrvtId = doc.createElement("PrvtId");
Id1.appendChild(PrvtId);
Element Othr = doc.createElement("Othr");
PrvtId.appendChild(Othr);
Element Id1_1 = doc.createElement("Id");
Othr.appendChild(Id1_1);
Id1_1.setTextContent("NL47ZZZ524034240000");

Element SchmeNm = doc.createElement("SchmeNm");
Othr.appendChild(SchmeNm);
Element Prtry1 = doc.createElement("Prtry");
SchmeNm.appendChild(Prtry1);
Prtry1.setTextContent("SEPA");

Element DrctDbtTxInf = doc.createElement("DrctDbtTxInf");
PmtInf.appendChild(DrctDbtTxInf);
Element PmtId = doc.createElement("PmtId");
DrctDbtTxInf.appendChild(PmtId);
Element EndToEndId = doc.createElement("EndToEndId");
PmtId.appendChild(EndToEndId);
EndToEndId.setTextContent("77012280");


Element InstdAmt = doc.createElement("InstdAmt");
DrctDbtTxInf.appendChild(InstdAmt);
InstdAmt.setAttribute("Ccy","EUR");
//InstdAmt.setAttribute("Ccy","EUR");
InstdAmt.setTextContent("31");

Element DrctDbtTx = doc.createElement("DrctDbtTx");
DrctDbtTxInf.appendChild(DrctDbtTx);

Element MndtRltdInf = doc.createElement("MndtRltdInf");
DrctDbtTx.appendChild(MndtRltdInf);
Element MndtId = doc.createElement("MndtId");
MndtRltdInf.appendChild(MndtId);
MndtId.setTextContent("41010");

Element DtOfSgntr = doc.createElement("DtOfSgntr");
MndtRltdInf.appendChild(DtOfSgntr);
DtOfSgntr.setTextContent(OnlyDate);
Element AmdmntInd = doc.createElement("AmdmntInd");
MndtRltdInf.appendChild(AmdmntInd);
AmdmntInd.setTextContent("false");

Element CdtrSchmeId1 = doc.createElement("CdtrSchmeId");
DrctDbtTx.appendChild(CdtrSchmeId1);
Element Id11 = doc.createElement("Id");
CdtrSchmeId1.appendChild(Id11);
Element PrvtId1 = doc.createElement("PrvtId");
Id11.appendChild(PrvtId1);
Element Othr1 = doc.createElement("Othr");
PrvtId1.appendChild(Othr1);
Element Id1_11 = doc.createElement("Id");
Othr1.appendChild(Id1_11);
Id1_11.setTextContent("NL47ZZZ524034240000");

Element SchmeNm1 = doc.createElement("SchmeNm");
Othr1.appendChild(SchmeNm1);
Element Prtry11 = doc.createElement("Prtry");
SchmeNm1.appendChild(Prtry11);
Prtry11.setTextContent("SEPA");

//DrctDbtTxInf

Element DbtrAgt = doc.createElement("DbtrAgt");
DrctDbtTxInf.appendChild(DbtrAgt);
Element FinInstnId1 = doc.createElement("FinInstnId");
DbtrAgt.appendChild(FinInstnId1);
Element BICFI1 = doc.createElement("BICFI");
FinInstnId1.appendChild(BICFI1);
BICFI1.setTextContent("RABONL2U");

//Dbtr

Element Dbtr = doc.createElement("Dbtr");
DrctDbtTxInf.appendChild(Dbtr);
Element Nm11 = doc.createElement("Nm");
Dbtr.appendChild(Nm11);
Nm11.setTextContent("T. Vsnmsno s/n Q.A.Q. Awmmzwzo");

Element DbtrAcct1 = doc.createElement("DbtrAcct");
DrctDbtTxInf.appendChild(DbtrAcct1);
Element Id_1_New = doc.createElement("Id");
DbtrAcct1.appendChild(Id_1_New);
Element IBAN1 = doc.createElement("IBAN");
Id_1_New.appendChild(IBAN1);
IBAN1.setTextContent("NL12RABO0100001213");

//RmtInf
Element RmtInf1 = doc.createElement("RmtInf");
DrctDbtTxInf.appendChild(RmtInf1);
Element Ustrd1 = doc.createElement("Ustrd");
RmtInf1.appendChild(Ustrd1);
Ustrd1.setTextContent("Qucpaaf pukeg KIA");

//Amount2 

////tag PmtInf
Element PmtInf2 = doc.createElement("PmtInf");
CstmrDrctDbtInitn.appendChild(PmtInf2);
Element PmtInfId2 = doc.createElement("PmtInfId");
PmtInf2.appendChild(PmtInfId2);
PmtInfId2.setTextContent("7012281");

Element PmtMtd2 = doc.createElement("PmtMtd");
PmtInf2.appendChild(PmtMtd2);
PmtMtd2.setTextContent("DD");

Element BtchBookg2 = doc.createElement("BtchBookg");
PmtInf2.appendChild(BtchBookg2);
BtchBookg2.setTextContent("false");

Element NbOfTxs2 = doc.createElement("NbOfTxs");
PmtInf2.appendChild(NbOfTxs2);
NbOfTxs2.setTextContent("1");

Element CtrlSum2 = doc.createElement("CtrlSum");
PmtInf2.appendChild(CtrlSum2);
CtrlSum2.setTextContent("32");

//Create Element for OrgnlMsgNmId
Element PmtTpInf2 = doc.createElement("PmtTpInf");
PmtInf2.appendChild(PmtTpInf2);
Element SvcLv122 = doc.createElement("SvcLvl");
PmtTpInf2.appendChild(SvcLv122);
Element Cd2_n = doc.createElement("Cd");
SvcLv122.appendChild(Cd2_n);
Cd2_n.setTextContent("SEPA");

//Create Element for PmtTpInf
Element LclInstrm2 = doc.createElement("LclInstrm");
PmtTpInf2.appendChild(LclInstrm2);
Element Cd22 = doc.createElement("Cd");
LclInstrm2.appendChild(Cd22);
Cd22.setTextContent("CORE");

Element SeqTp2 = doc.createElement("SeqTp");
PmtTpInf2.appendChild(SeqTp2);
SeqTp2.setTextContent("RCUR");

Element ReqdColltnDt2 = doc.createElement("ReqdColltnDt");
PmtInf2.appendChild(ReqdColltnDt2);
ReqdColltnDt2.setTextContent(OnlyDate);

Element Cdtr2 = doc.createElement("Cdtr");
PmtInf2.appendChild(Cdtr2);
Element Nm2 = doc.createElement("Nm");
Cdtr2.appendChild(Nm2);
Nm2.setTextContent("Pxcyoixxs Psusvxxiusi Zxih");

//Elemet inside PstlAdr
Element PstlAdr12 = doc.createElement("PstlAdr");
Cdtr2.appendChild(PstlAdr12);
Element Dept12 = doc.createElement("Dept");
PstlAdr12.appendChild(Dept12);
Dept12.setTextContent("Bank");

Element SubDept12 = doc.createElement("SubDept");
PstlAdr12.appendChild(SubDept12);
SubDept12.setTextContent("Payments");

Element StrtNm12 = doc.createElement("StrtNm");
PstlAdr12.appendChild(StrtNm12);
StrtNm12.setTextContent("Milkyway");

Element BldgNb12 = doc.createElement("BldgNb");
PstlAdr12.appendChild(BldgNb12);
BldgNb12.setTextContent("35");

Element BldgNm12 = doc.createElement("BldgNm");
PstlAdr12.appendChild(BldgNm12);
BldgNm12.setTextContent("Haagsepoort");

Element Flr12 = doc.createElement("Flr");
PstlAdr12.appendChild(Flr12);
Flr12.setTextContent("10");

Element PstBx12 = doc.createElement("PstBx");
PstlAdr12.appendChild(PstBx12);
PstBx12.setTextContent("1000");

Element Room12 = doc.createElement("Room");
PstlAdr12.appendChild(Room12);
Room12.setTextContent("D");

Element PstCd12 = doc.createElement("PstCd");
PstlAdr12.appendChild(PstCd12);
PstCd12.setTextContent("2595AK");

Element TwnNm12 = doc.createElement("TwnNm");
PstlAdr12.appendChild(TwnNm12);
TwnNm12.setTextContent("Den Haag");

Element TwnLctnNm12 = doc.createElement("TwnLctnNm");
PstlAdr12.appendChild(TwnLctnNm12);
TwnLctnNm12.setTextContent("Muziekbuurt");

Element DstrctNm12 = doc.createElement("DstrctNm");
PstlAdr12.appendChild(DstrctNm12);
DstrctNm12.setTextContent("Rijswijk");

Element CtrySubDvsn12 = doc.createElement("CtrySubDvsn");
PstlAdr12.appendChild(CtrySubDvsn12);
CtrySubDvsn12.setTextContent("Zuid-Holland");

Element Ctry12 = doc.createElement("Ctry");
PstlAdr12.appendChild(Ctry12);
Ctry12.setTextContent("NL");

//////Element inside  CdtrAcct
Element CdtrAcct12 = doc.createElement("CdtrAcct");
PmtInf2.appendChild(CdtrAcct12);
Element ID2 = doc.createElement("Id");
CdtrAcct12.appendChild(ID2);
Element IBAN2 = doc.createElement("IBAN");
ID2.appendChild(IBAN2);
IBAN2.setTextContent("NL97NNBA2022645367");

//////CdtrAgt
Element CdtrAgt2 = doc.createElement("CdtrAgt");
PmtInf2.appendChild(CdtrAgt2);
Element FinInstnId22 = doc.createElement("FinInstnId");
CdtrAgt2.appendChild(FinInstnId22);
Element BICFI22 = doc.createElement("BICFI");
FinInstnId22.appendChild(BICFI22);
BICFI22.setTextContent("NNBANL2G");

Element ChrgBr2 = doc.createElement("ChrgBr");
PmtInf2.appendChild(ChrgBr2);
ChrgBr2.setTextContent("SLEV");

Element CdtrSchmeId2 = doc.createElement("CdtrSchmeId");
PmtInf2.appendChild(CdtrSchmeId2);
Element Id222 = doc.createElement("Id");
CdtrSchmeId2.appendChild(Id222);
Element PrvtId2 = doc.createElement("PrvtId");
Id222.appendChild(PrvtId2);
Element Othr2 = doc.createElement("Othr");
PrvtId2.appendChild(Othr2);
Element Id1_2 = doc.createElement("Id");
Othr2.appendChild(Id1_2);
Id1_2.setTextContent("NL47ZZZ524034240000");

Element SchmeNm2 = doc.createElement("SchmeNm");
Othr2.appendChild(SchmeNm2);
Element Prtry2 = doc.createElement("Prtry");
SchmeNm2.appendChild(Prtry2);
Prtry2.setTextContent("SEPA");

Element DrctDbtTxInf2 = doc.createElement("DrctDbtTxInf");
PmtInf2.appendChild(DrctDbtTxInf2);
Element PmtId2 = doc.createElement("PmtId");
DrctDbtTxInf2.appendChild(PmtId2);
Element EndToEndId2 = doc.createElement("EndToEndId");
PmtId2.appendChild(EndToEndId2);
EndToEndId2.setTextContent("77012281");


Element InstdAmt2 = doc.createElement("InstdAmt");
DrctDbtTxInf2.appendChild(InstdAmt2);
InstdAmt2.setAttribute("Ccy","EUR");
InstdAmt2.setTextContent("32");

Element DrctDbtTx2 = doc.createElement("DrctDbtTx");
DrctDbtTxInf2.appendChild(DrctDbtTx2);

Element MndtRltdInf2 = doc.createElement("MndtRltdInf");
DrctDbtTx2.appendChild(MndtRltdInf2);
Element MndtId2 = doc.createElement("MndtId");
MndtRltdInf2.appendChild(MndtId2);
MndtId2.setTextContent("41011");

Element DtOfSgntr2 = doc.createElement("DtOfSgntr");
MndtRltdInf2.appendChild(DtOfSgntr2);
DtOfSgntr2.setTextContent(OnlyDate);
Element AmdmntInd2 = doc.createElement("AmdmntInd");
MndtRltdInf2.appendChild(AmdmntInd2);
AmdmntInd2.setTextContent("false");

Element CdtrSchmeId12 = doc.createElement("CdtrSchmeId");
DrctDbtTx2.appendChild(CdtrSchmeId12);
Element Id112 = doc.createElement("Id");
CdtrSchmeId12.appendChild(Id112);
Element PrvtId122 = doc.createElement("PrvtId");
Id112.appendChild(PrvtId122);
Element Othr12 = doc.createElement("Othr");
PrvtId122.appendChild(Othr12);
Element Id1_112 = doc.createElement("Id");
Othr12.appendChild(Id1_112);
Id1_112.setTextContent("NL47ZZZ524034240000");

Element SchmeNm12 = doc.createElement("SchmeNm");
Othr12.appendChild(SchmeNm12);
Element Prtry112 = doc.createElement("Prtry");
SchmeNm12.appendChild(Prtry112);
Prtry112.setTextContent("SEPA");


//DrctDbtTxInf

Element DbtrAgt22 = doc.createElement("DbtrAgt");
DrctDbtTxInf2.appendChild(DbtrAgt22);
Element FinInstnId222_n = doc.createElement("FinInstnId");
DbtrAgt22.appendChild(FinInstnId222_n);
Element BICFI222_n = doc.createElement("BICFI");
FinInstnId222_n.appendChild(BICFI222_n);
BICFI222_n.setTextContent("INGBNL2A");

//Dbtr

Element Dbtr2 = doc.createElement("Dbtr");
DrctDbtTxInf2.appendChild(Dbtr2);
Element Nm112 = doc.createElement("Nm");
Dbtr2.appendChild(Nm112);
Nm112.setTextContent("T. Vsnmsno s/n Q.A.Q. Awmmzwzo");

Element DbtrAcct12 = doc.createElement("DbtrAcct");
DrctDbtTxInf2.appendChild(DbtrAcct12);
Element Id_1_New2= doc.createElement("Id");
DbtrAcct12.appendChild(Id_1_New2);
Element IBAN12 = doc.createElement("IBAN");
Id_1_New2.appendChild(IBAN12);
IBAN12.setTextContent("NL43INGB7777779928");

//RmtInf
Element RmtInf12_n = doc.createElement("RmtInf");
DrctDbtTxInf2.appendChild(RmtInf12_n);
Element Ustrd12 = doc.createElement("Ustrd");
RmtInf12_n.appendChild(Ustrd12);
Ustrd12.setTextContent("Qucpaaf pukeg KIA");


//Amount3 

////tag PmtInf
Element PmtInf3 = doc.createElement("PmtInf");
CstmrDrctDbtInitn.appendChild(PmtInf3);
Element PmtInfId3 = doc.createElement("PmtInfId");
PmtInf3.appendChild(PmtInfId3);
PmtInfId3.setTextContent("7012282");

Element PmtMtd3 = doc.createElement("PmtMtd");
PmtInf3.appendChild(PmtMtd3);
PmtMtd3.setTextContent("DD");

Element BtchBookg3 = doc.createElement("BtchBookg");
PmtInf3.appendChild(BtchBookg3);
BtchBookg3.setTextContent("false");

Element NbOfTxs3 = doc.createElement("NbOfTxs");
PmtInf3.appendChild(NbOfTxs3);
NbOfTxs3.setTextContent("1");

Element CtrlSum3 = doc.createElement("CtrlSum");
PmtInf3.appendChild(CtrlSum3);
CtrlSum3.setTextContent("40");

//Create Element for OrgnlMsgNmId
Element PmtTpInf3 = doc.createElement("PmtTpInf");
PmtInf3.appendChild(PmtTpInf3);
Element SvcLv140 = doc.createElement("SvcLvl");
PmtTpInf3.appendChild(SvcLv140);
Element Cd3_n = doc.createElement("Cd");
SvcLv140.appendChild(Cd3_n);
Cd3_n.setTextContent("SEPA");

//Create Element for PmtTpInf
Element LclInstrm3 = doc.createElement("LclInstrm");
PmtTpInf3.appendChild(LclInstrm3);
Element Cd33 = doc.createElement("Cd");
LclInstrm3.appendChild(Cd33);
Cd33.setTextContent("CORE");

Element SeqTp3 = doc.createElement("SeqTp");
PmtTpInf3.appendChild(SeqTp3);
SeqTp3.setTextContent("RCUR");

Element ReqdColltnDt3 = doc.createElement("ReqdColltnDt");
PmtInf3.appendChild(ReqdColltnDt3);
ReqdColltnDt3.setTextContent(OnlyDate);

Element Cdtr3 = doc.createElement("Cdtr");
PmtInf3.appendChild(Cdtr3);
Element Nm3 = doc.createElement("Nm");
Cdtr3.appendChild(Nm3);
Nm3.setTextContent("Pxcyoixxs Psusvxxiusi Zxih");

//Elemet inside PstlAdr
Element PstlAdr13 = doc.createElement("PstlAdr");
Cdtr3.appendChild(PstlAdr13);
Element Dept13 = doc.createElement("Dept");
PstlAdr13.appendChild(Dept13);
Dept13.setTextContent("Bank");

Element SubDept13 = doc.createElement("SubDept");
PstlAdr13.appendChild(SubDept13);
SubDept13.setTextContent("Payments");

Element StrtNm13 = doc.createElement("StrtNm");
PstlAdr13.appendChild(StrtNm13);
StrtNm13.setTextContent("Milkyway");

Element BldgNb13 = doc.createElement("BldgNb");
PstlAdr13.appendChild(BldgNb13);
BldgNb13.setTextContent("35");

Element BldgNm13 = doc.createElement("BldgNm");
PstlAdr13.appendChild(BldgNm13);
BldgNm13.setTextContent("Haagsepoort");

Element Flr13 = doc.createElement("Flr");
PstlAdr13.appendChild(Flr13);
Flr13.setTextContent("10");

Element PstBx13 = doc.createElement("PstBx");
PstlAdr13.appendChild(PstBx13);
PstBx13.setTextContent("1000");

Element Room13 = doc.createElement("Room");
PstlAdr13.appendChild(Room13);
Room13.setTextContent("D");

Element PstCd13 = doc.createElement("PstCd");
PstlAdr13.appendChild(PstCd13);
PstCd13.setTextContent("2595AK");

Element TwnNm13 = doc.createElement("TwnNm");
PstlAdr13.appendChild(TwnNm13);
TwnNm13.setTextContent("Den Haag");

Element TwnLctnNm13 = doc.createElement("TwnLctnNm");
PstlAdr13.appendChild(TwnLctnNm13);
TwnLctnNm13.setTextContent("Muziekbuurt");

Element DstrctNm13 = doc.createElement("DstrctNm");
PstlAdr13.appendChild(DstrctNm13);
DstrctNm13.setTextContent("Rijswijk");

Element CtrySubDvsn13 = doc.createElement("CtrySubDvsn");
PstlAdr13.appendChild(CtrySubDvsn13);
CtrySubDvsn13.setTextContent("Zuid-Holland");

Element Ctry13 = doc.createElement("Ctry");
PstlAdr13.appendChild(Ctry13);
Ctry13.setTextContent("NL");

//////Element inside  CdtrAcct
Element CdtrAcct13 = doc.createElement("CdtrAcct");
PmtInf3.appendChild(CdtrAcct13);
Element ID3 = doc.createElement("Id");
CdtrAcct13.appendChild(ID3);
Element IBAN3 = doc.createElement("IBAN");
ID3.appendChild(IBAN3);
IBAN3.setTextContent("NL02NNBA3188204897");

//////CdtrAgt
Element CdtrAgt3 = doc.createElement("CdtrAgt");
PmtInf3.appendChild(CdtrAgt3);
Element FinInstnId33 = doc.createElement("FinInstnId");
CdtrAgt3.appendChild(FinInstnId33);
Element BICFI33 = doc.createElement("BICFI");
FinInstnId33.appendChild(BICFI33);
BICFI33.setTextContent("NNBANL2G");

Element ChrgBr3 = doc.createElement("ChrgBr");
PmtInf3.appendChild(ChrgBr3);
ChrgBr3.setTextContent("SLEV");

Element CdtrSchmeId3 = doc.createElement("CdtrSchmeId");
PmtInf3.appendChild(CdtrSchmeId3);
Element Id333 = doc.createElement("Id");
CdtrSchmeId3.appendChild(Id333);
Element PrvtId3 = doc.createElement("PrvtId");
Id333.appendChild(PrvtId3);
Element Othr3 = doc.createElement("Othr");
PrvtId3.appendChild(Othr3);
Element Id1_3 = doc.createElement("Id");
Othr3.appendChild(Id1_3);
Id1_3.setTextContent("NL47ZZZ524034240000");

Element SchmeNm3 = doc.createElement("SchmeNm");
Othr3.appendChild(SchmeNm3);
Element Prtry3 = doc.createElement("Prtry");
SchmeNm3.appendChild(Prtry3);
Prtry3.setTextContent("SEPA");

Element DrctDbtTxInf3 = doc.createElement("DrctDbtTxInf");
PmtInf3.appendChild(DrctDbtTxInf3);
Element PmtId3 = doc.createElement("PmtId");
DrctDbtTxInf3.appendChild(PmtId3);
Element EndToEndId3 = doc.createElement("EndToEndId");
PmtId3.appendChild(EndToEndId3);
EndToEndId3.setTextContent("77012282");


Element InstdAmt3 = doc.createElement("InstdAmt");
DrctDbtTxInf3.appendChild(InstdAmt3);
InstdAmt3.setAttribute("Ccy","EUR");
InstdAmt3.setTextContent("33");

Element DrctDbtTx3 = doc.createElement("DrctDbtTx");
DrctDbtTxInf3.appendChild(DrctDbtTx3);

Element MndtRltdInf3 = doc.createElement("MndtRltdInf");
DrctDbtTx3.appendChild(MndtRltdInf3);
Element MndtId3 = doc.createElement("MndtId");
MndtRltdInf3.appendChild(MndtId3);
MndtId3.setTextContent("41012");

Element DtOfSgntr3 = doc.createElement("DtOfSgntr");
MndtRltdInf3.appendChild(DtOfSgntr3);
DtOfSgntr3.setTextContent(OnlyDate);
Element AmdmntInd3 = doc.createElement("AmdmntInd");
MndtRltdInf3.appendChild(AmdmntInd3);
AmdmntInd3.setTextContent("false");

Element CdtrSchmeId13 = doc.createElement("CdtrSchmeId");
DrctDbtTx3.appendChild(CdtrSchmeId13);
Element Id113 = doc.createElement("Id");
CdtrSchmeId13.appendChild(Id113);
Element PrvtId133 = doc.createElement("PrvtId");
Id113.appendChild(PrvtId133);
Element Othr13 = doc.createElement("Othr");
PrvtId133.appendChild(Othr13);
Element Id1_113 = doc.createElement("Id");
Othr13.appendChild(Id1_113);
Id1_113.setTextContent("NL47ZZZ524034240000");

Element SchmeNm13 = doc.createElement("SchmeNm");
Othr13.appendChild(SchmeNm13);
Element Prtry113 = doc.createElement("Prtry");
SchmeNm13.appendChild(Prtry113);
Prtry113.setTextContent("SEPA");


//DrctDbtTxInf

Element DbtrAgt33 = doc.createElement("DbtrAgt");
DrctDbtTxInf3.appendChild(DbtrAgt33);
Element FinInstnId333_n = doc.createElement("FinInstnId");
DbtrAgt33.appendChild(FinInstnId333_n);
Element BICFI333_n = doc.createElement("BICFI");
FinInstnId333_n.appendChild(BICFI333_n);
BICFI333_n.setTextContent("DEUTESBB");

//Dbtr

Element Dbtr3 = doc.createElement("Dbtr");
DrctDbtTxInf3.appendChild(Dbtr3);
Element Nm113 = doc.createElement("Nm");
Dbtr3.appendChild(Nm113);
Nm113.setTextContent("T. Vsnmsno s/n Q.A.Q. Awmmzwzo");

Element DbtrAcct13 = doc.createElement("DbtrAcct");
DrctDbtTxInf3.appendChild(DbtrAcct13);
Element Id_1_New3= doc.createElement("Id");
DbtrAcct13.appendChild(Id_1_New3);
Element IBAN13 = doc.createElement("IBAN");
Id_1_New3.appendChild(IBAN13);
IBAN13.setTextContent("ES3300190029114010023693");

//RmtInf
Element RmtInf13_n = doc.createElement("RmtInf");
DrctDbtTxInf3.appendChild(RmtInf13_n);
Element Ustrd13 = doc.createElement("Ustrd");
RmtInf13_n.appendChild(Ustrd13);
Ustrd13.setTextContent("Qucpaaf pukeg KIA");


////Amount4 

////tag PmtInf
Element PmtInf4 = doc.createElement("PmtInf");
CstmrDrctDbtInitn.appendChild(PmtInf4);
Element PmtInfId4 = doc.createElement("PmtInfId");
PmtInf4.appendChild(PmtInfId4);
PmtInfId4.setTextContent("7012283");

Element PmtMtd4 = doc.createElement("PmtMtd");
PmtInf4.appendChild(PmtMtd4);
PmtMtd4.setTextContent("DD");

Element BtchBookg4 = doc.createElement("BtchBookg");
PmtInf4.appendChild(BtchBookg4);
BtchBookg4.setTextContent("false");

Element NbOfTxs4 = doc.createElement("NbOfTxs");
PmtInf4.appendChild(NbOfTxs4);
NbOfTxs4.setTextContent("1");

Element CtrlSum4 = doc.createElement("CtrlSum");
PmtInf4.appendChild(CtrlSum4);
CtrlSum4.setTextContent("30");

//Create Element for OrgnlMsgNmId
Element PmtTpInf4 = doc.createElement("PmtTpInf");
PmtInf4.appendChild(PmtTpInf4);
Element SvcLv144 = doc.createElement("SvcLvl");
PmtTpInf4.appendChild(SvcLv144);
Element Cd4_n = doc.createElement("Cd");
SvcLv144.appendChild(Cd4_n);
Cd4_n.setTextContent("SEPA");

//Create Element for PmtTpInf
Element LclInstrm4 = doc.createElement("LclInstrm");
PmtTpInf4.appendChild(LclInstrm4);
Element Cd44 = doc.createElement("Cd");
LclInstrm4.appendChild(Cd44);
Cd44.setTextContent("CORE");

Element SeqTp4 = doc.createElement("SeqTp");
PmtTpInf4.appendChild(SeqTp4);
SeqTp4.setTextContent("RCUR");

Element ReqdColltnDt4 = doc.createElement("ReqdColltnDt");
PmtInf4.appendChild(ReqdColltnDt4);
ReqdColltnDt4.setTextContent(OnlyDate);

Element Cdtr4 = doc.createElement("Cdtr");
PmtInf4.appendChild(Cdtr4);
Element Nm4 = doc.createElement("Nm");
Cdtr4.appendChild(Nm4);
Nm4.setTextContent("Pxcyoixxs Psusvxxiusi Zxih");

//Elemet inside PstlAdr
Element PstlAdr14 = doc.createElement("PstlAdr");
Cdtr4.appendChild(PstlAdr14);
Element Dept14 = doc.createElement("Dept");
PstlAdr14.appendChild(Dept14);
Dept14.setTextContent("Bank");

Element SubDept14 = doc.createElement("SubDept");
PstlAdr14.appendChild(SubDept14);
SubDept14.setTextContent("Payments");

Element StrtNm14 = doc.createElement("StrtNm");
PstlAdr14.appendChild(StrtNm14);
StrtNm14.setTextContent("Milkyway");

Element BldgNb14 = doc.createElement("BldgNb");
PstlAdr14.appendChild(BldgNb14);
BldgNb14.setTextContent("35");

Element BldgNm14 = doc.createElement("BldgNm");
PstlAdr14.appendChild(BldgNm14);
BldgNm14.setTextContent("Haagsepoort");

Element Flr14 = doc.createElement("Flr");
PstlAdr14.appendChild(Flr14);
Flr14.setTextContent("10");

Element PstBx14 = doc.createElement("PstBx");
PstlAdr14.appendChild(PstBx14);
PstBx14.setTextContent("1000");

Element Room14 = doc.createElement("Room");
PstlAdr14.appendChild(Room14);
Room14.setTextContent("D");

Element PstCd14 = doc.createElement("PstCd");
PstlAdr14.appendChild(PstCd14);
PstCd14.setTextContent("2595AK");

Element TwnNm14 = doc.createElement("TwnNm");
PstlAdr14.appendChild(TwnNm14);
TwnNm14.setTextContent("Den Haag");

Element TwnLctnNm14 = doc.createElement("TwnLctnNm");
PstlAdr14.appendChild(TwnLctnNm14);
TwnLctnNm14.setTextContent("Muziekbuurt");

Element DstrctNm14 = doc.createElement("DstrctNm");
PstlAdr14.appendChild(DstrctNm14);
DstrctNm14.setTextContent("Rijswijk");

Element CtrySubDvsn14 = doc.createElement("CtrySubDvsn");
PstlAdr14.appendChild(CtrySubDvsn14);
CtrySubDvsn14.setTextContent("Zuid-Holland");

Element Ctry14 = doc.createElement("Ctry");
PstlAdr14.appendChild(Ctry14);
Ctry14.setTextContent("NL");

//////Element inside  CdtrAcct
Element CdtrAcct14 = doc.createElement("CdtrAcct");
PmtInf4.appendChild(CdtrAcct14);
Element ID4 = doc.createElement("Id");
CdtrAcct14.appendChild(ID4);
Element IBAN4 = doc.createElement("IBAN");
ID4.appendChild(IBAN4);
IBAN4.setTextContent("NL21NNBA1000000567");

//////CdtrAgt
Element CdtrAgt4 = doc.createElement("CdtrAgt");
PmtInf4.appendChild(CdtrAgt4);
Element FinInstnId44 = doc.createElement("FinInstnId");
CdtrAgt4.appendChild(FinInstnId44);
Element BICFI44 = doc.createElement("BICFI");
FinInstnId44.appendChild(BICFI44);
BICFI44.setTextContent("NNBANL2G");

Element ChrgBr4 = doc.createElement("ChrgBr");
PmtInf4.appendChild(ChrgBr4);
ChrgBr4.setTextContent("SLEV");

Element CdtrSchmeId4 = doc.createElement("CdtrSchmeId");
PmtInf4.appendChild(CdtrSchmeId4);
Element Id444 = doc.createElement("Id");
CdtrSchmeId4.appendChild(Id444);
Element PrvtId4 = doc.createElement("PrvtId");
Id444.appendChild(PrvtId4);
Element Othr4 = doc.createElement("Othr");
PrvtId4.appendChild(Othr4);
Element Id1_4 = doc.createElement("Id");
Othr4.appendChild(Id1_4);
Id1_4.setTextContent("NL47ZZZ524034240000");

Element SchmeNm4 = doc.createElement("SchmeNm");
Othr4.appendChild(SchmeNm4);
Element Prtry4 = doc.createElement("Prtry");
SchmeNm4.appendChild(Prtry4);
Prtry4.setTextContent("SEPA");

Element DrctDbtTxInf4 = doc.createElement("DrctDbtTxInf");
PmtInf4.appendChild(DrctDbtTxInf4);
Element PmtId4 = doc.createElement("PmtId");
DrctDbtTxInf4.appendChild(PmtId4);
Element EndToEndId4 = doc.createElement("EndToEndId");
PmtId4.appendChild(EndToEndId4);
EndToEndId4.setTextContent("77012283");


Element InstdAmt4 = doc.createElement("InstdAmt");
DrctDbtTxInf4.appendChild(InstdAmt4);
InstdAmt4.setAttribute("Ccy","EUR");
InstdAmt4.setTextContent("30");

Element DrctDbtTx4 = doc.createElement("DrctDbtTx");
DrctDbtTxInf4.appendChild(DrctDbtTx4);

Element MndtRltdInf4 = doc.createElement("MndtRltdInf");
DrctDbtTx4.appendChild(MndtRltdInf4);
Element MndtId4 = doc.createElement("MndtId");
MndtRltdInf4.appendChild(MndtId4);
MndtId4.setTextContent("41013");

Element DtOfSgntr4 = doc.createElement("DtOfSgntr");
MndtRltdInf4.appendChild(DtOfSgntr4);
DtOfSgntr4.setTextContent(OnlyDate);
Element AmdmntInd4 = doc.createElement("AmdmntInd");
MndtRltdInf4.appendChild(AmdmntInd4);
AmdmntInd4.setTextContent("false");

Element CdtrSchmeId14 = doc.createElement("CdtrSchmeId");
DrctDbtTx4.appendChild(CdtrSchmeId14);
Element Id114 = doc.createElement("Id");
CdtrSchmeId14.appendChild(Id114);
Element PrvtId144 = doc.createElement("PrvtId");
Id114.appendChild(PrvtId144);
Element Othr14 = doc.createElement("Othr");
PrvtId144.appendChild(Othr14);
Element Id1_114 = doc.createElement("Id");
Othr14.appendChild(Id1_114);
Id1_114.setTextContent("NL47ZZZ524034240000");

Element SchmeNm14 = doc.createElement("SchmeNm");
Othr14.appendChild(SchmeNm14);
Element Prtry114 = doc.createElement("Prtry");
SchmeNm14.appendChild(Prtry114);
Prtry114.setTextContent("SEPA");


//DrctDbtTxInf

Element DbtrAgt44 = doc.createElement("DbtrAgt");
DrctDbtTxInf4.appendChild(DbtrAgt44);
Element FinInstnId444_n = doc.createElement("FinInstnId");
DbtrAgt44.appendChild(FinInstnId444_n);
Element BICFI444_n = doc.createElement("BICFI");
FinInstnId444_n.appendChild(BICFI444_n);
BICFI444_n.setTextContent("ABNANL2A");

//Dbtr

Element Dbtr4 = doc.createElement("Dbtr");
DrctDbtTxInf4.appendChild(Dbtr4);
Element Nm114 = doc.createElement("Nm");
Dbtr4.appendChild(Nm114);
Nm114.setTextContent("T. Vsnmsno s/n Q.A.Q. Awmmzwzo");

Element DbtrAcct14 = doc.createElement("DbtrAcct");
DrctDbtTxInf4.appendChild(DbtrAcct14);
Element Id_1_New4= doc.createElement("Id");
DbtrAcct14.appendChild(Id_1_New4);
Element IBAN14 = doc.createElement("IBAN");
Id_1_New4.appendChild(IBAN14);
IBAN14.setTextContent("NL91ABNA0417164300");

//RmtInf
Element RmtInf14_n = doc.createElement("RmtInf");
DrctDbtTxInf4.appendChild(RmtInf14_n);
Element Ustrd14 = doc.createElement("Ustrd");
RmtInf14_n.appendChild(Ustrd14);
Ustrd14.setTextContent("Qucpaaf pukeg KIA");

////Amount5 

////tag PmtInf
Element PmtInf5 = doc.createElement("PmtInf");
CstmrDrctDbtInitn.appendChild(PmtInf5);
Element PmtInfId5 = doc.createElement("PmtInfId");
PmtInf5.appendChild(PmtInfId5);
PmtInfId5.setTextContent("7012284");

Element PmtMtd5 = doc.createElement("PmtMtd");
PmtInf5.appendChild(PmtMtd5);
PmtMtd5.setTextContent("DD");

Element BtchBookg5 = doc.createElement("BtchBookg");
PmtInf5.appendChild(BtchBookg5);
BtchBookg5.setTextContent("false");

Element NbOfTxs5 = doc.createElement("NbOfTxs");
PmtInf5.appendChild(NbOfTxs5);
NbOfTxs5.setTextContent("1");

Element CtrlSum5 = doc.createElement("CtrlSum");
PmtInf5.appendChild(CtrlSum5);
CtrlSum5.setTextContent("40");

//Create Element for OrgnlMsgNmId
Element PmtTpInf5 = doc.createElement("PmtTpInf");
PmtInf5.appendChild(PmtTpInf5);
Element SvcLv155 = doc.createElement("SvcLvl");
PmtTpInf5.appendChild(SvcLv155);
Element Cd5_n = doc.createElement("Cd");
SvcLv155.appendChild(Cd5_n);
Cd5_n.setTextContent("SEPA");

//Create Element for PmtTpInf
Element LclInstrm5 = doc.createElement("LclInstrm");
PmtTpInf5.appendChild(LclInstrm5);
Element Cd55 = doc.createElement("Cd");
LclInstrm5.appendChild(Cd55);
Cd55.setTextContent("CORE");

Element SeqTp5 = doc.createElement("SeqTp");
PmtTpInf5.appendChild(SeqTp5);
SeqTp5.setTextContent("RCUR");

Element ReqdColltnDt5 = doc.createElement("ReqdColltnDt");
PmtInf5.appendChild(ReqdColltnDt5);
ReqdColltnDt5.setTextContent(OnlyDate);

Element Cdtr5 = doc.createElement("Cdtr");
PmtInf5.appendChild(Cdtr5);
Element Nm5 = doc.createElement("Nm");
Cdtr5.appendChild(Nm5);
Nm5.setTextContent("Pxcyoixxs Psusvxxiusi Zxih");

//Elemet inside PstlAdr
Element PstlAdr15 = doc.createElement("PstlAdr");
Cdtr5.appendChild(PstlAdr15);
Element Dept15 = doc.createElement("Dept");
PstlAdr15.appendChild(Dept15);
Dept15.setTextContent("Bank");

Element SubDept15 = doc.createElement("SubDept");
PstlAdr15.appendChild(SubDept15);
SubDept15.setTextContent("Payments");

Element StrtNm15 = doc.createElement("StrtNm");
PstlAdr15.appendChild(StrtNm15);
StrtNm15.setTextContent("Milkyway");

Element BldgNb15 = doc.createElement("BldgNb");
PstlAdr15.appendChild(BldgNb15);
BldgNb15.setTextContent("35");

Element BldgNm15 = doc.createElement("BldgNm");
PstlAdr15.appendChild(BldgNm15);
BldgNm15.setTextContent("Haagsepoort");

Element Flr15 = doc.createElement("Flr");
PstlAdr15.appendChild(Flr15);
Flr15.setTextContent("10");

Element PstBx15 = doc.createElement("PstBx");
PstlAdr15.appendChild(PstBx15);
PstBx15.setTextContent("1000");

Element Room15 = doc.createElement("Room");
PstlAdr15.appendChild(Room15);
Room15.setTextContent("D");

Element PstCd15 = doc.createElement("PstCd");
PstlAdr15.appendChild(PstCd15);
PstCd15.setTextContent("2595AK");

Element TwnNm15 = doc.createElement("TwnNm");
PstlAdr15.appendChild(TwnNm15);
TwnNm15.setTextContent("Den Haag");

Element TwnLctnNm15 = doc.createElement("TwnLctnNm");
PstlAdr15.appendChild(TwnLctnNm15);
TwnLctnNm15.setTextContent("Muziekbuurt");

Element DstrctNm15 = doc.createElement("DstrctNm");
PstlAdr15.appendChild(DstrctNm15);
DstrctNm15.setTextContent("Rijswijk");

Element CtrySubDvsn15 = doc.createElement("CtrySubDvsn");
PstlAdr15.appendChild(CtrySubDvsn15);
CtrySubDvsn15.setTextContent("Zuid-Holland");

Element Ctry15 = doc.createElement("Ctry");
PstlAdr15.appendChild(Ctry15);
Ctry15.setTextContent("NL");

//////Element inside  CdtrAcct
Element CdtrAcct15 = doc.createElement("CdtrAcct");
PmtInf5.appendChild(CdtrAcct15);
Element ID5 = doc.createElement("Id");
CdtrAcct15.appendChild(ID5);
Element IBAN5 = doc.createElement("IBAN");
ID5.appendChild(IBAN5);
IBAN5.setTextContent("NL91NNBA1000000568");

//////CdtrAgt
Element CdtrAgt5 = doc.createElement("CdtrAgt");
PmtInf5.appendChild(CdtrAgt5);
Element FinInstnId55 = doc.createElement("FinInstnId");
CdtrAgt5.appendChild(FinInstnId55);
Element BICFI55 = doc.createElement("BICFI");
FinInstnId55.appendChild(BICFI55);
BICFI55.setTextContent("NNBANL2G");

Element ChrgBr5 = doc.createElement("ChrgBr");
PmtInf5.appendChild(ChrgBr5);
ChrgBr5.setTextContent("SLEV");

Element CdtrSchmeId5 = doc.createElement("CdtrSchmeId");
PmtInf5.appendChild(CdtrSchmeId5);
Element Id555 = doc.createElement("Id");
CdtrSchmeId5.appendChild(Id555);
Element PrvtId5 = doc.createElement("PrvtId");
Id555.appendChild(PrvtId5);
Element Othr5 = doc.createElement("Othr");
PrvtId5.appendChild(Othr5);
Element Id1_5 = doc.createElement("Id");
Othr5.appendChild(Id1_5);
Id1_5.setTextContent("NL47ZZZ524034240000");

Element SchmeNm5 = doc.createElement("SchmeNm");
Othr5.appendChild(SchmeNm5);
Element Prtry5 = doc.createElement("Prtry");
SchmeNm5.appendChild(Prtry5);
Prtry5.setTextContent("SEPA");

Element DrctDbtTxInf5 = doc.createElement("DrctDbtTxInf");
PmtInf5.appendChild(DrctDbtTxInf5);
Element PmtId5 = doc.createElement("PmtId");
DrctDbtTxInf5.appendChild(PmtId5);
Element EndToEndId5 = doc.createElement("EndToEndId");
PmtId5.appendChild(EndToEndId5);
EndToEndId5.setTextContent("77012284");


Element InstdAmt5 = doc.createElement("InstdAmt");
DrctDbtTxInf5.appendChild(InstdAmt5);
InstdAmt5.setAttribute("Ccy","EUR");
InstdAmt5.setTextContent("40");

Element DrctDbtTx5 = doc.createElement("DrctDbtTx");
DrctDbtTxInf5.appendChild(DrctDbtTx5);

Element MndtRltdInf5 = doc.createElement("MndtRltdInf");
DrctDbtTx5.appendChild(MndtRltdInf5);
Element MndtId5 = doc.createElement("MndtId");
MndtRltdInf5.appendChild(MndtId5);
MndtId5.setTextContent("41014");

Element DtOfSgntr5 = doc.createElement("DtOfSgntr");
MndtRltdInf5.appendChild(DtOfSgntr5);
DtOfSgntr5.setTextContent(OnlyDate);
Element AmdmntInd5 = doc.createElement("AmdmntInd");
MndtRltdInf5.appendChild(AmdmntInd5);
AmdmntInd5.setTextContent("false");

Element CdtrSchmeId15 = doc.createElement("CdtrSchmeId");
DrctDbtTx5.appendChild(CdtrSchmeId15);
Element Id115 = doc.createElement("Id");
CdtrSchmeId15.appendChild(Id115);
Element PrvtId155 = doc.createElement("PrvtId");
Id115.appendChild(PrvtId155);
Element Othr15 = doc.createElement("Othr");
PrvtId155.appendChild(Othr15);
Element Id1_115 = doc.createElement("Id");
Othr15.appendChild(Id1_115);
Id1_115.setTextContent("NL47ZZZ524034240000");

Element SchmeNm15 = doc.createElement("SchmeNm");
Othr15.appendChild(SchmeNm15);
Element Prtry115 = doc.createElement("Prtry");
SchmeNm15.appendChild(Prtry115);
Prtry115.setTextContent("SEPA");


//DrctDbtTxInf

Element DbtrAgt55 = doc.createElement("DbtrAgt");
DrctDbtTxInf5.appendChild(DbtrAgt55);
Element FinInstnId555_n = doc.createElement("FinInstnId");
DbtrAgt55.appendChild(FinInstnId555_n);
Element BICFI555_n = doc.createElement("BICFI");
FinInstnId555_n.appendChild(BICFI555_n);
BICFI555_n.setTextContent("ABNANL2A");

//Dbtr

Element Dbtr5 = doc.createElement("Dbtr");
DrctDbtTxInf5.appendChild(Dbtr5);
Element Nm115 = doc.createElement("Nm");
Dbtr5.appendChild(Nm115);
Nm115.setTextContent("T. Vsnmsno s/n Q.A.Q. Awmmzwzo");

Element DbtrAcct15 = doc.createElement("DbtrAcct");
DrctDbtTxInf5.appendChild(DbtrAcct15);
Element Id_1_New5= doc.createElement("Id");
DbtrAcct15.appendChild(Id_1_New5);
Element IBAN15 = doc.createElement("IBAN");
Id_1_New5.appendChild(IBAN15);
IBAN15.setTextContent("NL91ABNA0417164300");

//RmtInf
Element RmtInf15_n = doc.createElement("RmtInf");
DrctDbtTxInf5.appendChild(RmtInf15_n);
Element Ustrd15 = doc.createElement("Ustrd");
RmtInf15_n.appendChild(Ustrd15);
Ustrd15.setTextContent("Qucpaaf pukeg KIA");

    
        
      

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("PAIN008CD.xml"));
        transformer.transform(source, result);


    }



}



