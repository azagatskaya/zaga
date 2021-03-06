package telran.util.memory;

public class MemoryService {
	public static int getAvailableMemoryBlockSize() {
		long size = Integer.MAX_VALUE;
		long middleSize = 0;
		long leftSize = size;
		long rightSize = 0;
		//21    16   14   13   10                   0
		while (true) {
			try {
				byte[] ar = new byte[(int) size];
				if(middleSize != 0 && leftSize > rightSize) {
					ar = null;
					rightSize = middleSize + 1;
					middleSize = getMiddleSize(leftSize,rightSize);
					size = middleSize;
					ar = new byte[(int) size];
				}
				return (int) size;
			} catch (OutOfMemoryError e) {
				if (middleSize != 0 && leftSize > rightSize) {
					leftSize = middleSize;
					middleSize = getMiddleSize(leftSize -1,rightSize);
					size = middleSize;
				} else if (middleSize == 0) {
					leftSize--;
					middleSize = getMiddleSize(leftSize,rightSize);
					size = middleSize;
				}
			}
		}
	}

	private static long getMiddleSize(long leftSize, long rightSize) {
		return (leftSize + rightSize) / 2;
	}
}
