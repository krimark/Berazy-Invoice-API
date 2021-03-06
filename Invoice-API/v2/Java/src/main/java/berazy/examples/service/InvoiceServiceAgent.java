package berazy.examples.service;

/**
 * Berazy Invoice SOAP API Client
 *
 * @author    <a href="mailto:johan@berazy.se">Johan Sall Larsson</a>
 * @version   1.0.0
 *
 * MIT LICENSE
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
 
import http.schema_invoice_berazy_se.v2.InvoiceService;
import http.schema_invoice_berazy_se.v2.InvoiceService_Service;

/**
 * Service singleton.
 *
 * @author <a href="mailto:johan@berazy.se">Johan Sall Larsson</a>
 * @since  1.0.0
 */
public class InvoiceServiceAgent {

    /**
     * Thread safe instance.
     */
    static InvoiceServiceAgent instance;
    
    /**
     * Read lock to prevent re-initialization.
     */
    static final Object readLock = new Object();
    
    /**
     * The invoice service.
     */
    InvoiceService invoiceService;
    
    /**
     * Default constructor.
     */
    InvoiceServiceAgent() {
        invoiceService = new InvoiceService_Service().getInvoiceServicePort();
    }
    
    /**
     * Returns the thread safe instance.
     * @return InvoiceServiceAgent
     */
    public static InvoiceServiceAgent getInstance() {
        if (instance == null) {
            synchronized(readLock) {
                if (instance == null) {
                   instance = new InvoiceServiceAgent();
                }
            }
        }
        return instance;
    }
    
    /**
     * Returns the service.
     * @return InvoiceService
     */
    public InvoiceService getService() {
        return invoiceService;
    }
    
}
