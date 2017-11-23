module FairWarning where


import Data.List

fairWarning :: [Int] -> Int
fairWarning ts = (d - head ts) `mod` d
  where
    d = foldl1' gcd $ (-) <$> ts <*> ts
