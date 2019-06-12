package com.gospell.chitong.rdcenter.broadcast.netty.util;

/**
 * @ClassName Crc32Util @Description TODO @Author pay @DATE 2019/5/23 10:02
 */
public class Crc32Util {
    public static AlgoParams Crc32Mpeg2 = new AlgoParams ("CRC-32/MPEG-2", 32, 0x04C11DB7L, 0xFFFFFFFFL, false, false, 0x00000000L, 0x0376E6E7L);
    public static CrcCalculator calculator = new CrcCalculator (Crc32Mpeg2);

    public static boolean check(byte[] bytes){
        byte[] subBytes1 = ByteUtils.subBytes (bytes, 0, bytes.length - 4);
        byte[] subBytes2 =  ByteUtils.subBytes (bytes, bytes.length - 4, 4);
        String result = getCrc32Result (subBytes1);
        String contrast = ByteUtils.Bytes2HexString (subBytes2);
        return result.toUpperCase ().equals (contrast.toUpperCase ());
    }
    public static String getCrc32Result(byte[] bytes) {
        long result = calculator.Calc (bytes, 0, bytes.length);
        return Long.toHexString (result).toUpperCase ();
    }

    private static class AlgoParams {
        public AlgoParams(String name, int hashSize, long poly, long init, boolean refIn, boolean refOut, long xorOut, long check) {
            Name = name;
            Check = check;
            Init = init;
            Poly = poly;
            RefIn = refIn;
            RefOut = refOut;
            XorOut = xorOut;
            HashSize = hashSize;
        }

        public long Check;
        public int HashSize;
        public long Init;
        public String Name;
        public long Poly;
        public boolean RefIn;
        public boolean RefOut;
        public long XorOut;
    }

    private static class CrcCalculator {
        public AlgoParams Parameters;
        public byte HashSize = 8;
        private long _mask = 0xFFFFFFFFFFFFFFFFL;
        private long[] _table = new long[256];
        public static final byte[] TestBytes = new byte[]{49, 50, 51, 52, 53, 54, 55, 56, 57};

        public CrcCalculator(AlgoParams params) {
            Parameters = params;
            HashSize = (byte) params.HashSize;
            if (HashSize < 64) _mask = (1L << HashSize) - 1;
            CreateTable ();
        }

        public long Calc(byte[] data, int offset, int length) {
            long init = Parameters.RefOut ? CrcHelper.ReverseBits (Parameters.Init, HashSize) : Parameters.Init;
            long hash = ComputeCrc (init, data, offset, length);
            return (hash ^ Parameters.XorOut) & _mask;
        }

        private long ComputeCrc(long init, byte[] data, int offset, int length) {
            long crc = init;
            if (Parameters.RefOut) for (int i = offset; i < offset + length; i++) {
                crc = (_table[(int) ((crc ^ data[i]) & 0xFF)] ^ (crc >>> 8));
                crc &= _mask;
            }else {
                int toRight = (HashSize - 8);
                toRight = toRight < 0 ? 0 : toRight;
                for (int i = offset; i < offset + length; i++) {
                    crc = (_table[(int) (((crc >> toRight) ^ data[i]) & 0xFF)] ^ (crc << 8));
                    crc &= _mask;
                }
            }
            return crc;
        }

        private void CreateTable() {
            for (int i = 0; i < _table.length; i++) _table[i] = CreateTableEntry (i);
        }

        private long CreateTableEntry(int index) {
            long r = (long) index;
            if (Parameters.RefIn) r = CrcHelper.ReverseBits (r, HashSize);
            else if (HashSize > 8) r <<= (HashSize - 8);
            long lastBit = (1L << (HashSize - 1));
            for (int i = 0; i < 8; i++)
                if ((r & lastBit) != 0) r = ((r << 1) ^ Parameters.Poly);
                else r <<= 1;
            if (Parameters.RefOut) r = CrcHelper.ReverseBits (r, HashSize);
            return r & _mask;
        }
    }

    private static class CrcHelper {
        static long ReverseBits(long ul, int valueLength) {
            long newValue = 0;
            for (int i = valueLength - 1; i >= 0; i--) {
                newValue |= (ul & 1) << i;
                ul >>= 1;
            }
            return newValue;
        }
    }
}
