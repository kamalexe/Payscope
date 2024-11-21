package pay.scope.payscope.Model;

public class InvoiceModel {

   private int InvoiceSNo;
   private String InvoiceDescription;
   private String InvoiceQty;
   private String InvoiceRate;
   private double InvoiceAmount;

    public InvoiceModel(int invoiceSNo, String invoiceDescription, String invoiceQty, String invoiceRate, double invoiceAmount, double v) {
        InvoiceSNo = invoiceSNo;
        InvoiceDescription = invoiceDescription;
        InvoiceQty = invoiceQty;
        InvoiceRate = invoiceRate;
        InvoiceAmount = invoiceAmount;
    }

    public int getInvoiceSNo() {
        return InvoiceSNo;
    }

    public void setInvoiceSNo(int invoiceSNo) {
        InvoiceSNo = invoiceSNo;
    }

    public String getInvoiceDescription() {
        return InvoiceDescription;
    }

    public void setInvoiceDescription(String invoiceDescription) {
        InvoiceDescription = invoiceDescription;
    }

    public String getInvoiceQty() {
        return InvoiceQty;
    }

    public void setInvoiceQty(String invoiceQty) {
        InvoiceQty = invoiceQty;
    }

    public String getInvoiceRate() {
        return InvoiceRate;
    }

    public void setInvoiceRate(String invoiceRate) {
        InvoiceRate = invoiceRate;
    }

    public double getInvoiceAmount() {
        return InvoiceAmount;
    }

    public void setInvoiceAmount(double invoiceAmount) {
        InvoiceAmount = invoiceAmount;
    }
}
