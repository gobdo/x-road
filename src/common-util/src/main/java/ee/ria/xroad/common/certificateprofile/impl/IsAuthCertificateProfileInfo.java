/**
 * The MIT License
 * Copyright (c) 2018 Estonian Information System Authority (RIA),
 * Nordic Institute for Interoperability Solutions (NIIS), Population Register Centre (VRK)
 * Copyright (c) 2015-2017 Estonian Information System Authority (RIA), Population Register Centre (VRK)
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
package ee.ria.xroad.common.certificateprofile.impl;

import ee.ria.xroad.common.certificateprofile.AuthCertificateProfileInfo;
import ee.ria.xroad.common.certificateprofile.DnFieldDescription;

/**
 * Default implementation (EJBCA) of AuthCertificateProfileInfo.
 */
public class IsAuthCertificateProfileInfo extends AbstractCertificateProfileInfo implements AuthCertificateProfileInfo {

    /**
     * Constructor.
     * 
     * @param params the parameters
     */
    public IsAuthCertificateProfileInfo(Parameters params) {
        super(new DnFieldDescription[] {
                // Instance identifier
                new EnumLocalizedFieldDescriptionImpl(
                        "C",
                        DnFieldLabelLocalizationKey.INSTANCE_IDENTIFIER,
                        params.getServerId().getXRoadInstance()
                ).setReadOnly(true),

                // Server code
                new EnumLocalizedFieldDescriptionImpl(
                        "CN",
                        DnFieldLabelLocalizationKey.SERVER_CODE,
                        params.getServerId().getServerCode()
                ).setReadOnly(true),

                // Serialnumber
                new EnumLocalizedFieldDescriptionImpl(
                        "serialNumber",
                        DnFieldLabelLocalizationKey.SERIAL_NUMBER,
                        params.getServerId().getXRoadInstance()
                        + "/" + params.getServerId().getServerCode()
                        + "/" + params.getServerId().getMemberClass()
                ).setReadOnly(true)
        });
    }
}
