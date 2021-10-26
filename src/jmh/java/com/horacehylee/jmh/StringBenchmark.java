package com.horacehylee.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
@Warmup(iterations = 2)
@Measurement(iterations = 2)
public class StringBenchmark {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(StringBenchmark.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }

    @Param({"100", "1000"})
    private int length;

    @Param({"a", "ab", "abc"})
    private String append;

    @Benchmark
    public String concatString() {
        String str = "";
        for (int i = 0; i < length; i++) {
            str += append;
        }
        return str;
    }

    @Benchmark
    public String stringBuilderLoop() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(append);
        }
        return sb.toString();
    }

    @Benchmark
    public String stringRepeat() {
        return String.valueOf(append).repeat(Math.max(0, length));
    }
}

