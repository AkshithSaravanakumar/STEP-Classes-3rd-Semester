public class Invoice implements Printable {

    private final String invoiceNumber;

    /**
     * @param invoiceNumber the invoice's number
     */
    public Invoice(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    @Override
    public String printLabel() {
        return "Invoice label: " + invoiceNumber;
    }
}