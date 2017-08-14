/*
 * Copyright (c) 2005, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package sample;

import org.apache.commons.lang.StringUtils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.osgl.util.S;

//https://stackoverflow.com/questions/16228992/commons-lang-stringutils-replace-performance-vs-string-replace
public class StringReplaceBenchmark {

	public static final String TGT = "A";
	public static final String REPLACEMENT = "B";

	@State(Scope.Thread)
    public static class BenchmarkState {
        volatile private String str = "AAAAAAAAAAAAABBBB";

    }
    
	@Benchmark
	public Object test4String(BenchmarkState state) {
		return state.str.replace(TGT, REPLACEMENT);
	}

	@Benchmark
	public Object test4StringUtils(BenchmarkState state) {
		return StringUtils.replace(state.str, TGT, REPLACEMENT);
	}

	@Benchmark
	public Object test4lang3StringUtils(BenchmarkState state) {
		return org.apache.commons.lang3.StringUtils.replace(state.str, TGT, REPLACEMENT);
	}

	@Benchmark
	public Object test4Osgl(BenchmarkState state) {
		return S.have(state.str).replace(TGT).with(REPLACEMENT);
	}
	

	@Benchmark
	public Object test4fast(BenchmarkState state) {
		return replace(state.str, TGT, REPLACEMENT);
	}
    
	public static String replace (String source, String os, String ns) {
	    if (source == null) {
	        return null;
	    }
	    int i = 0;
	    if ((i = source.indexOf(os, i)) >= 0) {
	        char[] sourceArray = source.toCharArray();
	        char[] nsArray = ns.toCharArray();
	        int oLength = os.length();
	        StringBuilder buf = new StringBuilder (sourceArray.length);
	        buf.append (sourceArray, 0, i).append(nsArray);
	        i += oLength;
	        int j = i;
	        // Replace all remaining instances of oldString with newString.
	        while ((i = source.indexOf(os, i)) > 0) {
	            buf.append (sourceArray, j, i - j).append(nsArray);
	            i += oLength;
	            j = i;
	        }
	        buf.append (sourceArray, j, sourceArray.length - j);
	        source = buf.toString();
	        buf.setLength (0);
	    }
	    return source;
	}

	
	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder().include(StringReplaceBenchmark.class.getSimpleName()).warmupIterations(5)
				.measurementIterations(5).forks(1).build();
		new Runner(opt).run();
	}

}
