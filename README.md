# これは何か

書籍 [Getting Started with Hazelcast](https://www.packtpub.com/big-data-and-business-intelligence/getting-started-hazelcast) (Kindle版であればAmazon.co.jpでも購入できます)の内容を理解するために, 書籍の内容を私が書き写したものです.
ただし, 書籍が対象としているHazelcastのバージョンが2.6であるのに対し, 私が用いているバージョンは現時点での最新版3.4であるため修正している箇所もあります.

# 備考

## Hazelcast 2.6 と 3.4 の差異

### TestApp

com.hazelcast.examples.TestApp というクラスは無くなり, 代わりに
com.hazelcast.console.ConsoleApp というクラスになっています.
おそらく3.3以降からの変更です.
(参考: https://github.com/hazelcast/hazelcast/pull/2683 )

### Predicates

引数の型や戻り値の型が大きく変わっています.
https://github.com/hazelcast/hazelcast/commit/c127cde52cfa89018605f361ea8f513b4a8da34f

