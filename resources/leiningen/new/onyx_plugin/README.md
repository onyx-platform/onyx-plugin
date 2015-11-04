## onyx-{{medium}}

Onyx plugin for {{medium}}.

#### Installation

In your project file:

```clojure
[onyx-{{medium}} "0.8.0.0-alpha1"]
```

In your peer boot-up namespace:

```clojure
(:require [onyx.plugin.{{medium}}])
```

#### Functions

##### sample-entry

Catalog entry:

```clojure
{:onyx/name :entry-name
 :onyx/plugin :onyx.plugin.{{medium}}/input
 :onyx/type :input
 :onyx/medium :{{medium}}
 :onyx/batch-size batch-size
 :onyx/doc "Reads segments from {{medium}}"}
```

Lifecycle entry:

```clojure
[{:lifecycle/task :your-task-name
  :lifecycle/calls :onyx.plugin.{{medium}}/lifecycle-calls}]
```

#### Attributes

|key                           | type      | description
|------------------------------|-----------|------------
|`:{{medium}}/attr`            | `string`  | Description here.

#### Contributing

Pull requests into the master branch are welcomed.

#### License

Copyright © 2015 FIX ME

Distributed under the Eclipse Public License, the same as Clojure.
