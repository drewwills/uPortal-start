/**
 * Copyright � 2001 The JA-SIG Collaborative.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. Redistributions of any form whatsoever must retain the following
 *    acknowledgment:
 *    "This product includes software developed by the JA-SIG Collaborative
 *    (http://www.jasig.org/)."
 *
 * THIS SOFTWARE IS PROVIDED BY THE JA-SIG COLLABORATIVE "AS IS" AND ANY
 * EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE JA-SIG COLLABORATIVE OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package org.jasig.portal.utils;

import java.io.Writer;
import java.io.IOException;


/**
 * A filter presenting a <code>Writer</code> that performs
 * word substitution (search&replace) on the fly.
 *
 * @author <a href="mailto:pkharchenko@interactivebusiness.com">Peter Kharchenko</a>
 * @version: $Revision$
 */
public class SubstitutionWriter extends Writer {
    SubstitutionIntegerFilter filter;

    /**
     * Creates a new <code>SubstitutionWriter</code> instance.
     *
     * @param out a true <code>Writer</code> value where processed stream should be directed
     * @param target a <code>byte[]</code> value of a target to be replaced
     * @param substitute a <code>byte[]</code> value with which the target will be replaced
     */
    public SubstitutionWriter(Writer out, char[] target, char[] substitute) {
        // form integer arrays 
        int[] itarget=new int[target.length];
        for(int i=0;i<target.length;i++) {
            itarget[i]=(int)target[i];
        }

        int[] isubstitute=new int[substitute.length];
        for(int i=0;i<substitute.length;i++) {
            isubstitute[i]=(int)substitute[i];
        }

        filter=new SubstitutionIntegerFilter(new WriteableWriterWrapper(out),itarget,isubstitute);
    }

    public void write(int i) throws IOException {
        filter.write(i);
    }

    public void flush() throws IOException {
        filter.flush();
    }
    public void close() throws IOException {
        filter.close();
    }

    public void write(char[] cbuf, int off, int len) throws IOException {
        // check boundaries
        if(off+len>cbuf.length) throw new IOException("Invalid offsent or length specified");

        for(int i=0;i<len;i++) {
            filter.write((int)cbuf[i]);
        }
    }
    

    /**
     * A test self-test method for the class.
     *
     */
    public static void main(String[] args) {
        // construct a string
        String inputString="Marry had a little lamb, little lamb, little lamb.";
        
        // set out the sink
        java.io.StringWriter sw=new java.io.StringWriter();
        SubstitutionWriter substw=new SubstitutionWriter(sw,(new String("lamb")).toCharArray(),(new String("rump")).toCharArray());
        try {
            substw.write(inputString);
            substw.flush(); 
            String resultString=sw.toString();
            if(resultString.equals("Marry had a little rump, little rump, little rump.")) {
               System.out.println("Test passed.");
            } else {
               System.out.println("Test failed!");
            }
        } catch (Exception e) {
            System.out.println("Test failed:");
            e.printStackTrace();
        }
                                                         
    }
            
}
