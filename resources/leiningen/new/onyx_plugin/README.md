## onyx-core-{{medium}}

Onyx plugin for {{medium}}.

#### Installation

In your project file:

```clojure
[onyx-{{medium}} "0.4.0-SNAPSHOT"]
```

In your peer boot-up namespace:

```clojure
(:require [onyx.plugin.{{medium}}])
```

#### Catalog entries

##### sample-entry

```clojure
{:onyx/name :entry-name
 :onyx/ident :{{medium}}/task
 :onyx/type :input
 :onyx/medium :{{medium}}
 :onyx/consumption :concurrent
 :onyx/batch-size batch-size
 :onyx/doc "Reads segments from {{medium}}"}
```

#### Attributes

|key                           | type      | description
|------------------------------|-----------|------------
|`:{{medium}}/attr`            | `string`  | Description here.

#### Lifecycle Arguments

##### `sample-entry`

```clojure
(defmethod l-ext/inject-lifecycle-resources :{{medium}}/task
  [_ _] {:{{medium}}/arg val})
```

#### Contributing

Pull requests into the master branch are welcomed.

#### License

Copyright © 2014 FIX ME

Distributed under the Eclipse Public License, the same as Clojure.