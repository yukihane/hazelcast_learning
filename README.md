# これは何か

書籍 [Getting Started with Hazelcast](https://www.packtpub.com/big-data-and-business-intelligence/getting-started-hazelcast) (Kindle版であればAmazon.co.jpでも購入できます)の内容を理解するために, 書籍の内容を私が書き写したものです.
ただし, 書籍が対象としているHazelcastのバージョンが2.6であるのに対し, 私が用いているバージョンは現時点での最新版3.4であるため修正している箇所もあります.

なお, 出版社のページから正式なコードをダウンロードすることができます(私は参照していませんが).
* https://www.packtpub.com/books/content/support/13880


# 備考

## Hazelcast 2.6 と 3.4 の差異

Hazelcast公式blogに[What's new in Hazelcast 3?](http://blog.hazelcast.com/2013/06/03/whats-new-in-hazelcast-3/)というエントリがありましたので, こちらも参考になるかもしれません.

また, マニュアルには[Upgrading from 2.x versions](http://docs.hazelcast.org/docs/3.4/manual/html/upgradingfrom2x.html)というセクションがあります.

### TestApp

com.hazelcast.examples.TestApp というクラスは無くなり, 代わりに
com.hazelcast.console.ConsoleApp というクラスになっています.
おそらく3.3以降からの変更です.
(参考: https://github.com/hazelcast/hazelcast/pull/2683 )

### Predicates

引数の型や戻り値の型が大きく変わっています.
おそらく3.0からの変更です.
https://github.com/hazelcast/hazelcast/commit/c127cde52cfa89018605f361ea8f513b4a8da34f

### cluster_wide_map_size (hazelcast.xml内)

cluster_wide_map_size という設定はなくなっています.
https://github.com/hazelcast/hazelcast/commit/fcc78a596a58ac9c74380dcd18e5218dfccb3b58

ver.2では
* map_size_per_jvm
* cluster_wide_map_size
* partitions_wide_map_size
* used_heap_size
* used_heap_percentage

の5種類だったものが, ver.3では
* PER_NODE (上のURLの変更ではPER_JVMとなっていますが, それから更に変更されています)
* PER_PARTITION
* USED_HEAP_PERCENTAGE
* USED_HEAP_SIZE

の4種類になっていました.

本書の文脈的には, PER_NODE で置換すれば良いと思われます.

余談になりますが, [マニュアル](http://docs.hazelcast.org/docs/3.4/manual/html-single/hazelcast-documentation.html)には下記の記述があります.
> In 2.x releases, the default value for max-size eviction policy was cluster_wide_map_size. In 3.x releases, default is PER_NODE. After upgrading, the max-size should be set according to this new default, if it is not changed. Otherwise, it is likely that OutOfMemory exception may be thrown.

### HazelcastInstance#getTransaction()

getTransaction() というメソッドは無くなっており, 代わりに newTransactionContext(TransactionOptions) を使用すべきであるように見えます.
https://github.com/hazelcast/hazelcast/commit/6657a3dad1d1c5631f71f0d23f1ad397169a5666

[マニュアル](http://docs.hazelcast.org/docs/3.4/manual/html-single/hazelcast-documentation.html#transaction-interface)にコードサンプルがあるのですが, このように実装してもrepeatable readになりませんでした(transaction中の2回目のgetでConsoleAppの方で変更した内容に変わってしまう).

これについては[#4414](https://github.com/hazelcast/hazelcast/issues/4414)としてissueを挙げてみました.

### EntryListener

2つのメソッドが追加されていました.
* [Adds new method evictAll to IMap #2706](https://github.com/hazelcast/hazelcast/pull/2706)
* [mapCleared is added to EntryListener #2790](https://github.com/hazelcast/hazelcast/pull/2790)

なお, 次バージョンのv3.5でも変更が入るように見えます.
* [Introduces new MapListener interface and sub-interfaces #4370](https://github.com/hazelcast/hazelcast/pull/4370)

ここでは以下のjavadocコメントが追加されています.
> This interface is here for backward compatibility reasons. For a most appropriate alternative please use/check link com.hazelcast.map.listener.MapListener interface.

### InstanceListener

DistributedObjectListener に名称変更されたようです.

https://github.com/hazelcast/hazelcast/commit/220818af62eb756aca41b2b883019e57c6144917
