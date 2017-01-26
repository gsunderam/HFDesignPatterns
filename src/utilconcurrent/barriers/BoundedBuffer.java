package utilconcurrent.barriers;

import java.util.concurrent.Semaphore;

public class BoundedBuffer<V>  {
		final Semaphore availItems, availSpaces;
	  final V items [];
		public int putPos = 0, takePos = 0;

    public BoundedBuffer() {
        this(11);
    }

    public BoundedBuffer(int size) {
			availSpaces = new Semaphore(size);
			availItems = new Semaphore(0);
			items = (V[]) new Object[size];
    }

    public void put(V v) throws InterruptedException {
			availSpaces.acquire();
			doInsert(v);
			availItems.release();
    }

		private synchronized void doInsert(V v) {
			items[putPos] = v;
			putPos = ( putPos + 1) % items.length;
		}

		public V take() throws InterruptedException {
			availItems.acquire();
			V v = doExtract();
			availSpaces.release();
			return v;
		}

		private synchronized V doExtract() {
			V v = items[takePos];
			items[takePos] = null;
			takePos = (takePos + 1) % items.length;
			return v;
		}

		public boolean isFull() {
       return availSpaces.availablePermits() == 0;
    }

    public boolean isEmpty() {
       return availItems.availablePermits() == 0;
    }

	  public int checkPermits() {
			return availSpaces.availablePermits() + availItems.availablePermits();
		}

	  public void printBuffer() {
			short i = 0;
			for(V v : items)
				System.out.println("Item " + (++i) + " is " + v);
		}
}
