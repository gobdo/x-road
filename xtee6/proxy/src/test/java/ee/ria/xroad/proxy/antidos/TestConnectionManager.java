/**
 * The MIT License
 * Copyright (c) 2015 Estonian Information System Authority (RIA), Population Register Centre (VRK)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ee.ria.xroad.proxy.antidos;

import static org.junit.Assert.assertEquals;

class TestConnectionManager
        extends AntiDosConnectionManager<TestSocketChannel> {

    private final TestSystemMetrics systemMetrics;

    TestConnectionManager(TestConfiguration configuration,
            TestSystemMetrics systemMetrics) {
        super(configuration);
        this.systemMetrics = systemMetrics;
    }

    @Override
    protected TestSocketChannel getNextConnection()
            throws InterruptedException {
        systemMetrics.next();

        return super.getNextConnection();
    }

    @Override
    protected long getFreeFileDescriptorCount() {
        return systemMetrics.get().getMinFreeFileHandles();
    }

    @Override
    protected double getCpuLoad() {
        return systemMetrics.get().getMaxCpuLoad();
    }

    int numActivePartners() {
        return activePartners.size();
    }

    void accept(TestSocketChannel... connections) {
        for (TestSocketChannel connection : connections) {
            accept(connection);
        }
    }

    void assertNextConnection(TestSocketChannel conn) throws Exception {
        assertEquals(conn, getNextConnection());
    }

    void assertConnections(TestSocketChannel... connections)
            throws Exception {
        for (TestSocketChannel connection : connections) {
            assertNextConnection(connection);
        }
    }

    void assertEmpty() {
        assertEquals(0, numActivePartners());
    }
}
