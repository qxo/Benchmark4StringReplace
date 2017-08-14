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
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.RunnerException;
import org.osgl.util.IO;
import org.osgl.util.S;

//https://stackoverflow.com/questions/16228992/commons-lang-stringutils-replace-performance-vs-string-replace
public class StringReplaceBenchmark {

    public static final String TGT_NO_MATCH = "XYZ";
    public static final String TGT = "AA";
    public static final String REPLACEMENT = "B";
    public static final String TEXT = "AAAAAAAAAABBB";

    public static final String TGT_NO_MATCH_LONG = "aaaxyz0001";
    public static final String TGT_LONG = "occurrence";
    public static final String REP_LONG = "appearance";
    public static final String TEXT_LONG = IO.readContentAsString(StringReplaceBenchmark.class.getResource("/long_str.txt"));

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        volatile private String str = TEXT;
        volatile private String strLong = TEXT_LONG;
    }

    @Benchmark
    public Object testString(BenchmarkState state) {
        return state.str.replace(TGT, REPLACEMENT);
    }

    @Benchmark
    public Object testStringUtils(BenchmarkState state) {
        return StringUtils.replace(state.str, TGT, REPLACEMENT);
    }

    @Benchmark
    public Object testLang3StringUtils(BenchmarkState state) {
        return org.apache.commons.lang3.StringUtils.replace(state.str, TGT, REPLACEMENT);
    }

    @Benchmark
    public Object testOsgl(BenchmarkState state) {
        return S.have(state.str).replace(TGT).with(REPLACEMENT);
    }

    @Benchmark
    public Object testFast(BenchmarkState state) {
        return replace(state.str, TGT, REPLACEMENT);
    }

    @Benchmark
    public Object testStringNoMatch(BenchmarkState state) {
        return state.str.replace(TGT_NO_MATCH, REPLACEMENT);
    }

    @Benchmark
    public Object testStringUtilsNoMatch(BenchmarkState state) {
        return StringUtils.replace(state.str, TGT_NO_MATCH, REPLACEMENT);
    }

    @Benchmark
    public Object testLang3StringUtilsNoMatch(BenchmarkState state) {
        return org.apache.commons.lang3.StringUtils.replace(state.str, TGT_NO_MATCH, REPLACEMENT);
    }

    @Benchmark
    public Object testOsglNoMatch(BenchmarkState state) {
        return S.have(state.str).replace(TGT_NO_MATCH).with(REPLACEMENT);
    }

    @Benchmark
    public Object testFastNoMatch(BenchmarkState state) {
        return replace(state.str, TGT_NO_MATCH, REPLACEMENT);
    }

    @Benchmark
    public Object testStringLong(BenchmarkState state) {
        return state.strLong.replace(TGT_LONG, REP_LONG);
    }

    @Benchmark
    public Object testStringUtilsLong(BenchmarkState state) {
        return StringUtils.replace(state.strLong, TGT_LONG, REP_LONG);
    }

    @Benchmark
    public Object testLang3StringUtilsLong(BenchmarkState state) {
        return org.apache.commons.lang3.StringUtils.replace(state.strLong, TGT_LONG, REP_LONG);
    }

    @Benchmark
    public Object testOsglLong(BenchmarkState state) {
        return S.have(state.strLong).replace(TGT_LONG).with(REP_LONG);
    }

    @Benchmark
    public Object testFastLong(BenchmarkState state) {
        return replace(state.strLong, TGT_LONG, REP_LONG);
    }


    @Benchmark
    public Object testStringLongNoMatch(BenchmarkState state) {
        return state.strLong.replace(TGT_NO_MATCH_LONG, REPLACEMENT);
    }

    @Benchmark
    public Object testStringUtilsLongNoMatch(BenchmarkState state) {
        return StringUtils.replace(state.strLong, TGT_NO_MATCH_LONG, REPLACEMENT);
    }

    @Benchmark
    public Object testLang3StringUtilsLongNoMatch(BenchmarkState state) {
        return org.apache.commons.lang3.StringUtils.replace(state.strLong, TGT_NO_MATCH_LONG, REPLACEMENT);
    }

    @Benchmark
    public Object testOsglLongNoMatch(BenchmarkState state) {
        return S.have(state.strLong).replace(TGT_NO_MATCH_LONG).with(REPLACEMENT);
    }

    @Benchmark
    public Object testFastLongNoMatch(BenchmarkState state) {
        return replace(state.strLong, TGT_NO_MATCH_LONG, REPLACEMENT);
    }

    public static String replace(String source, String os, String ns) {
        if (source == null) {
            return null;
        }
        int i = 0;
        if ((i = source.indexOf(os, i)) >= 0) {
            char[] sourceArray = source.toCharArray();
            char[] nsArray = ns.toCharArray();
            int oLength = os.length();
            StringBuilder buf = new StringBuilder(sourceArray.length);
            buf.append(sourceArray, 0, i).append(nsArray);
            i += oLength;
            int j = i;
            // Replace all remaining instances of oldString with newString.
            while ((i = source.indexOf(os, i)) > 0) {
                buf.append(sourceArray, j, i - j).append(nsArray);
                i += oLength;
                j = i;
            }
            buf.append(sourceArray, j, sourceArray.length - j);
            source = buf.toString();
            buf.setLength(0);
        }
        return source;
    }


    public static void main(String[] args) throws RunnerException {
        Object o = new StringReplaceBenchmark().testOsglLong(new BenchmarkState());
        System.out.println(o);
//        Options opt = new OptionsBuilder().include(StringReplaceBenchmark.class.getSimpleName()).warmupIterations(5)
//                .measurementIterations(5).forks(1).build();
//        new Runner(opt).run();
//		System.out.println(TEXT.replace(TGT, REPLACEMENT));
    }

}
